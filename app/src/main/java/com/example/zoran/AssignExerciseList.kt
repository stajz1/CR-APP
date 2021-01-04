package com.example.zoran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AssignExerciseList(private var mListItem : ArrayList<String>): RecyclerView.Adapter<AssignExerciseList.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.assign_exercise_list, parent , false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return mListItem.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    }
}