package com.example.zoran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zoran.fragmentPatientSide.PatientWorkOutViewModel
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.patient_input.view.*
import kotlinx.android.synthetic.main.patient_input.view.done
import kotlinx.android.synthetic.main.patient_input.view.progressBar3

class FragmentPatientInput: Fragment() {

    private var root: View? = null
    private var patientWorkOutViewModel:  PatientWorkOutViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.patient_input, container, false)
        val patientId = arguments?.getInt("patient_id")
        val exerciseId = arguments?.getInt("exercise_id")

        patientWorkOutViewModel = ViewModelProvider(this)[PatientWorkOutViewModel::class.java]

        root?.imageView12?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        patientWorkOutViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.done?.visibility = View.VISIBLE
                    root?.progressBar3?.visibility = View.GONE
                    it.error.printStackTrace()
                }

                is NetworkResponse.SUCCESS.AddWorkOut -> {
                    root?.done?.visibility = View.VISIBLE
                    root?.progressBar3?.visibility = View.GONE
                    if (it.response.status){

                        root?.done?.isEnabled = true
                        requireContext().toast("Übung hinzugefügt")
                        requireActivity().onBackPressed()

                    }else{
                        requireContext().toast("Failed to error")
                    }
                    Log.e("Status", it.response.status.toString())
                }

            }
        })
        root?.done?.setOnClickListener {
            root?.done?.visibility = View.GONE
            root?.progressBar3?.visibility = View.VISIBLE
            patientWorkOutViewModel?.addWorkOut(exerciseId!!, root?.textView24?.text.toString().toInt() ,patientId!!)
        }

        return root;
    }

}