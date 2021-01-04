package com.example.zoran

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
import com.example.zoran.fragmentStaffSide.AssignedExerciseViewModel
import com.example.zoran.fragmentStaffSide.ExerciseListViewModel
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.patient_exercise.view.*
import kotlinx.android.synthetic.main.patient_exercise.view.recyclerView

class FragmentPatientExercise: Fragment(), ExerciseList.onClick {

    private var root: View? = null
    private var exerciseList: ExerciseList?= null
    private var id: Int? = 0
    private var assignedExerciseViewModel:  AssignedExerciseViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.patient_exercise, container, false)

        id = arguments?.getInt("id")

        assignedExerciseViewModel = ViewModelProvider(this)[AssignedExerciseViewModel::class.java]

        assignedExerciseViewModel?.assignedExerciseList(id!!)
        exerciseList = ExerciseList(requireContext())
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
                }

                is NetworkResponse.SUCCESS.AssignedExerciseList -> {
                    root?.progressBar2?.visibility = GONE
                    exerciseList?.setData(it.response)
                }

            }
        })

        root?.imageView20?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return root;
    }


    override fun cardClick(exerciseId: Int) {
        val bundle = Bundle()
        id?.let { bundle.putInt("patient_id", it) }
        bundle.putInt("id", exerciseId)
        bundle.putInt("type", 1)
        findNavController().navigate(R.id.action_fragmentPatientExercise_to_fragmentExerciseDetails, bundle)
    }

}