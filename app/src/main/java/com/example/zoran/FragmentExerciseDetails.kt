package com.example.zoran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.zoran.fragmentStaffSide.ExerciseDetailsViewModel
import com.example.zoran.fragmentStaffSide.FragmentExerciseList
import com.example.zoran.network.NetworkResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.exercise_details.view.*
import kotlinx.android.synthetic.main.exercise_details.view.done
import kotlinx.android.synthetic.main.exercise_details.view.imageView12
import kotlinx.android.synthetic.main.exercise_details.view.progressBar3

class FragmentExerciseDetails: Fragment() {

    private var root: View? = null
    private var exerciseDetailsViewModel:  ExerciseDetailsViewModel? = null
    private var fragmentExerciseList: FragmentExerciseList? = null
    var patientId: Int? = 0
    var exerciseId: Int? = 0
    var type: Int? = 4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.exercise_details, container, false)

        val id: Int? = arguments?.getInt("id")
        Log.e("ExerciseId1", id.toString())
        exerciseId = id
        Log.e("ExerciseId2", exerciseId.toString())
        patientId = arguments?.getInt("patient_id")
        type = arguments?.getInt("type")

        requireContext().logError(patientId.toString())

        root?.imageView12?.setOnClickListener {
            requireActivity().onBackPressed()
        }

        if (patientId == 0){
            root?.done?.visibility = GONE
        }

        if (type == 1){
            root?.textView9?.text = "Gemacht"
        } else if (type == 0){
            root?.textView9?.text = "OK"
        } else if (type == 2){
            root?.textView9?.text = "Delete"
        }else {
            root?.done?.visibility = GONE

        }
        exerciseDetailsViewModel = ViewModelProvider(this)[ExerciseDetailsViewModel::class.java]

        fragmentExerciseList = FragmentExerciseList(requireContext())
        id?.let { exerciseDetailsViewModel?.exerciseDetails(it) }

        exerciseDetailsViewModel?.response?.observe(viewLifecycleOwner, Observer {

            when (it) {

                is NetworkResponse.ERROR -> {

                    it.error.printStackTrace()
                    root?.progressBar2?.visibility = GONE
                    requireContext().toast("Failed to request")

                }

                is NetworkResponse.SUCCESS.ExerciseDetails -> {

                    root?.progressBar2?.visibility = GONE

                    root?.selectableRoundedImageView2?.visibility = VISIBLE
                    root?.selectableRoundedImageView3?.visibility = VISIBLE
                    root?.done?.visibility = VISIBLE
                    root?.textView20?.visibility = VISIBLE
                    root?.textView18?.visibility = VISIBLE

                    Picasso.with(context).load(Constants.imgUrl + it.response.image1Name)
                        .into(
                            root?.selectableRoundedImageView2
                        )

                    Picasso.with(context).load(Constants.imgUrl + it.response.image2Name)
                        .into(
                            root?.selectableRoundedImageView3
                        )

                    root?.textView20?.text = it.response.exerciseDetail
                    root?.textView18?.text = it.response.exerciseName

                }

                is NetworkResponse.SUCCESS.AssignExercise -> {

                    root?.done?.visibility = VISIBLE
                    root?.progressBar3?.visibility = GONE

                    if (it.response.status){

                        root?.done?.isEnabled = true
                        requireContext().toast("Übung hinzugefügt")
                        requireActivity().onBackPressed()

                    }else{
                        requireContext().toast("Failed to error")
                    }

                }

                is NetworkResponse.SUCCESS.DeletePatientExercise -> {

                    requireActivity().onBackPressed()


                }

                else -> requireContext().toast("Failed to error")

            }

        })

        root?.done?.setOnClickListener {
            if (type == 0){

                root?.done?.visibility = GONE
                root?.progressBar3?.visibility = VISIBLE
                exerciseDetailsViewModel?.exerciseAssign(exerciseId!!, patientId!!)
            }else if (type == 1) {

                val bundle = Bundle()
                patientId?.let { bundle.putInt("patient_id", it) }
                exerciseId?.let { bundle.putInt("exercise_id", it) }
                findNavController().navigate(R.id.action_fragmentExerciseDetails_to_fragmentPatientInput, bundle)

            } else if (type == 2) {
                exerciseDetailsViewModel?.deleteAssignedExercise(exerciseId!!, patientId!!)
            }

        }

        return root;
    }

}