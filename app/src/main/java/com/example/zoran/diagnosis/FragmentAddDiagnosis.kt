package com.example.zoran.diagnosis

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zoran.R
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.fragment_add_diagnosis.view.*
import java.util.*

class FragmentAddDiagnosis: Fragment() {

    private var root: View? = null
    private var diagnosisViewModel:  DiagnosisViewModel? = null
    var patientId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_add_diagnosis, container, false)

        diagnosisViewModel = ViewModelProvider(this)[DiagnosisViewModel::class.java]
        patientId = arguments?.getInt("patient_id")

        diagnosisViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar4?.visibility = GONE
                    root?.button4?.isEnabled = true
                    it.error.printStackTrace()
                }

                is NetworkResponse.SUCCESS.AddDiagnosis -> {
                    requireActivity().onBackPressed()
                    root?.progressBar4?.visibility = GONE
                    root?.button4?.isEnabled = true
                }
            }
        })

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        root?.button4?.setOnClickListener {
            val imm =
                (Objects.requireNonNull(requireContext())
                    .getSystemService(
                        Activity.INPUT_METHOD_SERVICE
                    ) as InputMethodManager)
            imm.hideSoftInputFromWindow(root?.windowToken, 0)
            root?.progressBar4?.visibility = VISIBLE
            root?.button4?.isEnabled = false
            diagnosisViewModel?.storeDiagnosis(patientId!!, root?.editTextTextPersonName?.text.toString(), root?.editTextTextPersonName2?.text.toString(), root?.editTextTextPersonName3?.text.toString())
        }

        return root;
    }

}