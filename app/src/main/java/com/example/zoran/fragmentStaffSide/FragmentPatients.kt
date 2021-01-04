package com.example.zoran.fragmentStaffSide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoran.PatientList
import com.example.zoran.R
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.staff_patients.view.*
import com.example.zoran.toast

class FragmentPatients: Fragment(), PatientList.onClick {

    private var root: View? = null
    private var patientList: PatientList?= null
    private var patientListModel:  PatientListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staff_patients, container, false)

        patientListModel = ViewModelProvider(this)[PatientListViewModel::class.java]

        patientListModel?.patientList()



        patientList = PatientList()
        patientList?.setOnClick(this)
        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.setHasFixedSize(true)
        root?.recyclerView?.adapter = patientList

        root?.imageView19?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        patientListModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.PatientList -> {
                    root?.progressBar2?.visibility = GONE
                    patientList?.setData(it.response)
                }

            }
        })

        return root;
    }


    override fun cardClick(fullName: String, id: Int, age: String, diagnosis: String) {
        val bundle = Bundle()
        bundle.putString("fullName", fullName)
        bundle.putString("age", age)
        bundle.putString("diagnosis", diagnosis)
        bundle.putInt("id", id)
        findNavController().navigate(R.id.action_fragmentPatients_to_fragmentPatientInfo, bundle)
    }

}