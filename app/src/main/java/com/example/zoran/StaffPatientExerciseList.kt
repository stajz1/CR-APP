package com.example.zoran

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.fragmentStaffSide.AssignedExerciseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.patient_exercise_list.view.*

class StaffPatientExerciseList(var context: Context): RecyclerView.Adapter<StaffPatientExerciseList.ViewHolder>(){

    var mOnClick : onClick? = null
    var mAssignExercise: ArrayList<AssignedExerciseModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.patient_exercise_list, parent , false))

    fun setOnClick(onClick: onClick){
        mOnClick = onClick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = mAssignExercise[position].exercise.exerciseName
        Picasso.with(context).load(Constants.imgUrl + mAssignExercise[position].exercise.image1Name).into(holder.imageView6)
    }

    override fun getItemCount(): Int {
        return mAssignExercise.size
    }

    fun setData(assignExercise: ArrayList<AssignedExerciseModel>){
        mAssignExercise = assignExercise
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var cardView = itemView.cardView
        var imageView6 = itemView.imageView6
        var textView = itemView.textView
        var delete = itemView.delete

        init {
            cardView.setOnClickListener {
                mOnClick?.cardClick(mAssignExercise[adapterPosition].exercise.id)
            }

            delete.setOnClickListener {
                mOnClick?.deleteClick(mAssignExercise[adapterPosition].exercise.id)
                removeData(adapterPosition)
            }
        }
    }

    interface onClick{
        fun cardClick(id: Int)
        fun deleteClick(id: Int)
    }

    fun removeData(position: Int){
        mAssignExercise.remove(mAssignExercise[position])
        notifyDataSetChanged()
    }

}