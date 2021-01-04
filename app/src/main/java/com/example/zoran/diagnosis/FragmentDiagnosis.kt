package com.example.zoran.diagnosis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoran.R
import com.example.zoran.auth.AuthViewModel
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.fragment_diagnosis.view.*
import kotlinx.android.synthetic.main.fragment_diagnosis.view.add_diagnosis
import kotlinx.android.synthetic.main.fragment_diagnosis.view.imageView10
import kotlinx.android.synthetic.main.fragment_diagnosis.view.progressBar2
import kotlinx.android.synthetic.main.fragment_diagnosis.view.recyclerView

class FragmentDiagnosis: Fragment(), DiagnosisList.ClickListen {

    private var root: View? = null
    private var diagnosisList: DiagnosisList?= null
    private var diagnosisViewModel:  DiagnosisViewModel? = null
    var patientId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_diagnosis, container, false)

        diagnosisList = DiagnosisList()

        diagnosisList?.setOnClick(this)

        diagnosisViewModel = ViewModelProvider(this)[DiagnosisViewModel::class.java]
        patientId = arguments?.getInt("id")
        root?.textView34?.text = arguments?.getString("fullName")

        diagnosisViewModel?.diagnosisList(patientId!!)

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.setHasFixedSize(true)
        root?.recyclerView?.adapter = diagnosisList

        diagnosisViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = View.GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.DiagnosisList -> {
                    root?.progressBar2?.visibility = View.GONE
                    diagnosisList?.setData(it.response)
                }
            }
        })


        root?.add_diagnosis?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("patient_id", patientId!!)
            findNavController().navigate(R.id.action_fragmentDiagnosis_to_fragmentAddDiagnosis, bundle)

        }

        return root;
    }

    override fun deleteClick(id: Int) {
        Log.e("DeleteClick", id.toString())
        diagnosisViewModel?.deleteDiagnosis(id)
    }


}