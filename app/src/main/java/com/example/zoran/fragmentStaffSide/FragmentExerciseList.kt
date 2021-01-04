package com.example.zoran.fragmentStaffSide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.Constants
import com.example.zoran.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.excercise_list.view.*

class FragmentExerciseList(var context: Context): RecyclerView.Adapter<FragmentExerciseList.ViewHolder>(){

    var mOnClick : onClick? = null
    var mExercise: ArrayList<Exercise> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.excercise_list, parent , false))

    fun setOnClick(onClick: onClick){
        mOnClick = onClick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = mExercise[position].exerciseName
        Picasso.with(context).load(Constants.imgUrl + mExercise[position].image1Name).into(holder.imageView6)
    }

    override fun getItemCount(): Int {
        return mExercise.size
    }

    fun setData(assignExercise: ArrayList<Exercise>){
        mExercise = assignExercise
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var cardView = itemView.cardView
        var imageView6 = itemView.imageView6
        var textView = itemView.textView

        init {
            cardView.setOnClickListener {
                mOnClick?.cardClick(mExercise[adapterPosition].id)
            }
        }
    }

    interface onClick{
        fun cardClick(id: Int)
    }

}