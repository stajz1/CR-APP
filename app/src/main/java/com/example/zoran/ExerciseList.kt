package com.example.zoran

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.fragmentStaffSide.AssignedExerciseModel
import com.example.zoran.fragmentStaffSide.Exercise
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.excercise_list.view.*

class ExerciseList(private var context: Context): RecyclerView.Adapter<ExerciseList.ViewHolder>(){

    var mOnClick : onClick? = null
    var mExerciseList: ArrayList<AssignedExerciseModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.excercise_list, parent , false))

    fun setOnClick(onClick: onClick){
        mOnClick = onClick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text  = mExerciseList[position].exercise.exerciseName
        Picasso.with(context).load(Constants.imgUrl + mExerciseList[position].exercise.image1Name).into(holder.imageView6)
    }

    override fun getItemCount(): Int {
        return mExerciseList.size
    }

    fun setData(exerciseList: ArrayList<AssignedExerciseModel>){
        mExerciseList = exerciseList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var cardLayout = itemView.cardView
        var textView = itemView.textView
        var imageView6 = itemView.imageView6

        init {
            cardLayout.setOnClickListener {
                mOnClick?.cardClick(mExerciseList[adapterPosition].exercise.id)
            }
        }
    }


    interface onClick{
        fun cardClick(exerciseId: Int)
    }

}