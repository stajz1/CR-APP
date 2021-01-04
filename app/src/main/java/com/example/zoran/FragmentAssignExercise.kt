package com.example.zoran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.staff_exercise_assign.view.*

class FragmentAssignExercise: Fragment() {

    private var root: View? = null
    private var exerciseList: ExerciseList?= null
    private var arrayList : ArrayList<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.staff_exercise_assign, container, false)


/*
        setData()
        exerciseList = ExerciseList(arrayList)

        root?.recyclerView?.layoutManager = LinearLayoutManager(context)
        root?.recyclerView?.setHasFixedSize(true)
        root?.recyclerView?.adapter = exerciseList
*/

        return root;
    }

    fun setData(){
        arrayList.add("Data")
        arrayList.add("Data")
        arrayList.add("Data")
        arrayList.add("Data")
        arrayList.add("Data")
        arrayList.add("Data")
        arrayList.add("Data")
    }

}