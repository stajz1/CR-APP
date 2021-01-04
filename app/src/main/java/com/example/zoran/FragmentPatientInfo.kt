package com.example.zoran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.staff_patient_info.view.*
import kotlinx.android.synthetic.main.staff_patient_info.view.imageView10

class FragmentPatientInfo: Fragment() {

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staff_patient_info, container, false)

        root?.textView15?.text = arguments?.get("fullName").toString()
        root?.textView11?.text = "(Age ".plus(arguments?.get("age").toString()).plus(")")
        root?.textView17?.text = arguments?.get("diagnosis").toString()

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val id: Int? = arguments?.getInt("id")

        root?.cardView1?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView15?.text.toString())

            findNavController().navigate(R.id.action_fragmentPatientInfo_to_fragmentVitalSigns, bundle)
        }

        root?.cardView5?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView15?.text.toString())

            findNavController().navigate(R.id.action_fragmentPatientInfo_to_fragmentDiagnosis, bundle)
        }

        root?.cardView2?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView15?.text.toString())
            findNavController().navigate(R.id.action_fragmentPatientInfo_to_fragmentStaffPatientExercise, bundle)

        }

        root?.cardView3?.setOnClickListener {
            val bundle = Bundle()
            id?.let { bundle.putInt("id", it) }
            bundle.putString("fullName", root?.textView15?.text.toString())
            findNavController().navigate(R.id.action_fragmentPatientInfo_to_fragmentHistory, bundle)

        }

        return root;
    }

}