package com.example.zoran.fragmentPatientSide

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zoran.R
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.add_vital_signs.view.*
import java.util.*


class FragmentAddVitalSigns: Fragment() {

    private var root: View? = null
    private var vitalSignsListModel:  VitalSignsViewModel? = null
    private var patientId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.add_vital_signs, container, false)
        vitalSignsListModel = ViewModelProvider(this)[VitalSignsViewModel::class.java]

        val gender = resources.getStringArray(R.array.vital_signs)

        patientId = arguments?.getInt("id")

        root?.imageView12?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        vitalSignsListModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar3?.visibility =GONE
                    root?.done?.visibility = VISIBLE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to request")
                }

                is NetworkResponse.SUCCESS.AddVitalSigns -> {
                    root?.progressBar3?.visibility =GONE
                    root?.done?.visibility = VISIBLE
                    requireActivity().onBackPressed()
                }
            }
        })

        root?.textView23?.setOnClickListener {
            val alert: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            alert.setTitle("Vitalzeichen wählen")
            alert.setSingleChoiceItems(gender, -1
            ) { dialogInterface, which ->

                if (gender[which] == "Puls"){
                    root?.textView33?.visibility = VISIBLE
                }else{
                    root?.textView33?.visibility = GONE
                }

                root?.textView23?.text = gender[which]
                dialogInterface.dismiss()
            }
            alert.show()
        }

        root?.done?.setOnClickListener {
            val imm =
                (Objects.requireNonNull(requireContext())
                    .getSystemService(
                        Activity.INPUT_METHOD_SERVICE
                    ) as InputMethodManager)
            imm.hideSoftInputFromWindow(root?.windowToken, 0)
            root?.progressBar3?.visibility = VISIBLE
            root?.done?.visibility = GONE
            vitalSignsListModel?.storeDiagnosis(patientId!!,  root?.textView23?.text.toString(), root?.textView24?.text.toString(), root?.textView33?.text.toString())

        }

        return root;
    }

}