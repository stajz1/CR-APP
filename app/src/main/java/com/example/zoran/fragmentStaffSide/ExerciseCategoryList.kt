package com.example.zoran.fragmentStaffSide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.Constants
import com.example.zoran.R
import com.example.zoran.exercise.ExerciseCategoryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.assign_exercise_list.view.*

class ExerciseCategoryList(private var context: Context): RecyclerView.Adapter<ExerciseCategoryList.ViewHolder>(){

    var mOnClick : onClick? = null
    var exerciseCategoryModel: ArrayList<ExerciseCategoryModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.assign_exercise_list, parent , false))

    fun setOnClick(onClick: onClick){
        mOnClick = onClick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView12.text = exerciseCategoryModel[position].categoryName
        exerciseCategoryModel[position].categoryImage?.let {
            Picasso.with(context).load(Constants.imgUrl + exerciseCategoryModel[position].categoryImage).into(holder.selectableRoundedImageView)
        }
    }

    override fun getItemCount(): Int {
        return exerciseCategoryModel.size
    }

    fun setData(mExerciseCategoryModel: ArrayList<ExerciseCategoryModel>){
        exerciseCategoryModel = mExerciseCategoryModel
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var cardLayout = itemView.card_layout
        var textView12 = itemView.textView12
        var selectableRoundedImageView = itemView.selectableRoundedImageView

        init {
            cardLayout.setOnClickListener {
                mOnClick?.cardClick(exerciseCategoryModel[adapterPosition].id)
            }
        }
    }


    interface onClick{
        fun cardClick(id: Int)
    }

}