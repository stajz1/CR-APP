package com.example.zoran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.fragmentPatientSide.HistoryListModel
import kotlinx.android.synthetic.main.history_list.view.*

class HistoryList: RecyclerView.Adapter<HistoryList.ViewHolder>(){

    var mHistoryList: ArrayList<HistoryListModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.history_list, parent , false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView10.text = mHistoryList[position].exercise.exerciseName
        holder.steps.text = mHistoryList[position].steps
        holder.date.text = mHistoryList[position].createdAt
    }

    override fun getItemCount(): Int {
        return mHistoryList.size
    }

    fun setData(historyList: ArrayList<HistoryListModel>){
        mHistoryList = historyList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var textView10 = itemView.textView10
        var steps = itemView.imageView9
        var date = itemView.textView12
    }
}