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
import com.example.zoran.AutoFitGridLayoutManager
import com.example.zoran.R
import com.example.zoran.StaffPatientExerciseList
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.staff_patient_exercise.view.*

class FragmentStaffPatientExercise: Fragment(), StaffPatientExerciseList.onClick {

    private var root: View? = null
    private var exerciseList: StaffPatientExerciseList?= null
    private var assignedExerciseViewModel:  AssignedExerciseViewModel? = null
    private var exerciseDetailsViewModel:  ExerciseDetailsViewModel? = null

    var patientId: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.staff_patient_exercise, container, false)

        root?.textView13?.text = arguments?.getString("fullName")

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        exerciseDetailsViewModel = ViewModelProvider(this)[ExerciseDetailsViewModel::class.java]
        patientId = arguments?.getInt("id")
        Log.e("Patientid", patientId.toString())
        assignedExerciseViewModel = ViewModelProvider(this)[AssignedExerciseViewModel::class.java]

        patientId?.let { assignedExerciseViewModel?.assignedExerciseList(it) }

        exerciseList = StaffPatientExerciseList(requireContext())
        exerciseList?.setOnClick(this)

        val layoutManager =
            AutoFitGridLayoutManager(
                requireContext(),
                400
            )

        root?.recyclerView?.layoutManager = layoutManager
        root?.recyclerView?.adapter = exerciseList

        assignedExerciseViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.AssignedExerciseList -> {
                    root?.progressBar2?.visibility = GONE
                    exerciseList?.setData(it.response)
                }

            }
        })

        root?.add_exercise?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("patient_id", patientId!!)
            findNavController().navigate(R.id.action_fragmentStaffPatientExercise_to_fragmentExerciseCategory, bundle)
        }

        return root;
    }

    override fun cardClick(exerciseId: Int) {
    /*    val bundle = Bundle()
        bundle.putInt("id", exerciseId)
        bundle.putBoolean("detail", true)
        bundle.putInt("type", 2)
        bundle.putInt("patient_id", patientId!!)
        findNavController().navigate(R.id.action_fragmentStaffPatientExercise_to_fragmentExerciseDetails, bundle)*/
    }

    override fun deleteClick(exerciseId: Int) {
        exerciseDetailsViewModel?.deleteAssignedExercise(exerciseId, patientId!!)

    }

}