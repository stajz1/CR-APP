package com.example.zoran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zoran.fragmentStaffSide.ExerciseListViewModel
import com.example.zoran.fragmentStaffSide.FragmentExerciseList
import com.example.zoran.network.NetworkResponse
import kotlinx.android.synthetic.main.fragment_exercises.view.recyclerView
import kotlinx.android.synthetic.main.staff_exercise_category.view.*

class FragmentExercises: Fragment(), FragmentExerciseList.onClick {

    private var root: View? = null
    private var exerciseList: FragmentExerciseList?= null

    private var exerciseListViewModel:  ExerciseListViewModel? = null
    var patientId: Int? = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_exercises, container, false)
        val id: Int? = arguments?.getInt("id")
        Log.e("Id", id.toString())
        patientId = arguments?.getInt("patient_id")

        exerciseListViewModel = ViewModelProvider(this)[ExerciseListViewModel::class.java]

        exerciseList = FragmentExerciseList(requireContext())
        exerciseList?.setOnClick(this)
        id?.let { exerciseListViewModel?.exerciseList(it) }

        val layoutManager =
            AutoFitGridLayoutManager(
                requireContext(),
                400
            )
        root?.recyclerView?.layoutManager = layoutManager
        root?.recyclerView?.adapter = exerciseList

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        exerciseListViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = View.GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.ExerciseList -> {
                    root?.progressBar2?.visibility = View.GONE
                    exerciseList?.setData(it.response)
                }

            }
        })


        return root;
    }


    override fun cardClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        patientId?.let { bundle.putInt("patient_id", it) }
        Log.e("ExerciseIdId", id.toString())
        Log.e("patient_id", patientId.toString())
        findNavController().navigate(R.id.action_fragmentExercises2_to_fragmentExerciseDetails, bundle)
    }

}