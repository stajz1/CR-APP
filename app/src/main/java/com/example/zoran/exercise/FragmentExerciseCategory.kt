package com.example.zoran.exercise

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
import com.example.zoran.fragmentStaffSide.ExerciseCategoryList
import com.example.zoran.network.NetworkResponse
import com.example.zoran.toast
import kotlinx.android.synthetic.main.staff_exercise_category.view.*
import kotlinx.android.synthetic.main.staff_exercise_category.view.progressBar2
import kotlinx.android.synthetic.main.staff_exercise_category.view.recyclerView

class FragmentExerciseCategory: Fragment(), ExerciseCategoryList.onClick {

    private var root: View? = null
    private var exerciseCategoryList: ExerciseCategoryList?= null
    private var exerciseCategoryViewModel:  ExerciseCategoryViewModel? = null
    var patientId: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.staff_exercise_category, container, false)

        patientId = arguments?.getInt("patient_id")

        exerciseCategoryViewModel = ViewModelProvider(this)[ExerciseCategoryViewModel::class.java]

        exerciseCategoryList = ExerciseCategoryList(requireContext())

        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.adapter = exerciseCategoryList
        exerciseCategoryList?.setOnClick(this)

        exerciseCategoryViewModel?.exerciseCategoryList()

        root?.imageView10?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        exerciseCategoryViewModel?.response?.observe(viewLifecycleOwner, Observer {
            when(it){

                is NetworkResponse.ERROR->{
                    root?.progressBar2?.visibility = View.GONE
                    it.error.printStackTrace()
                    requireContext().toast("Failed to fetch list")
                }

                is NetworkResponse.SUCCESS.ExerciseCategoryList -> {
                    root?.progressBar2?.visibility = View.GONE
                    exerciseCategoryList?.setData(it.response)
                }

            }
        })

        return root;
    }

    override fun cardClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        patientId?.let { bundle.putInt("patient_id", it) }
        findNavController().navigate(R.id.action_fragmentExerciseCategory_to_fragmentExercises2, bundle)
    }

}