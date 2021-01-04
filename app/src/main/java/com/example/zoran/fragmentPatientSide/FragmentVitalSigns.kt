package com.example.zoran.fragmentPatientSide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoran.R
import com.example.zoran.VitalSignsList
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.fragment_vital_signs.view.*
import kotlinx.android.synthetic.main.fragment_vital_signs.view.progressBar2
import kotlinx.android.synthetic.main.fragment_vital_signs.view.recyclerView
import kotlinx.android.synthetic.main.fragment_vital_signs.view.textView13

class FragmentVitalSigns: Fragment() {

    private var root: View? = null
    private var vitalSignsList: VitalSignsList?= null
    private var vitalSignsListModel:  VitalSignsViewModel? = null
    private var patientId: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_vital_signs, container, false)

        vitalSignsListModel = ViewModelProvider(this)[VitalSignsViewModel::class.java]
        patientId = arguments?.getInt("id")
        root?.textView13?.text = arguments?.getString("fullName")

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        vitalSignsListModel?.vitalSignsList(patientId!!)

        vitalSignsList = VitalSignsList(requireContext())

        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.setHasFixedSize(true)
        root?.recyclerView?.adapter = vitalSignsList


        vitalSignsListModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = View.GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.VitalSignsList -> {
                    root?.progressBar2?.visibility = View.GONE
                    vitalSignsList?.setData(it.response)
                }
            }
        })


        root?.add_vital_signs?.setOnClickListener {
            val bundle = Bundle()
            patientId?.let { bundle.putInt("id", it) }
            findNavController().navigate(R.id.action_fragmentVitalSigns_to_fragmentAddVitalSigns, bundle)
        }

        return root;
    }


}