package com.example.zoran.diagnosis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.R
import kotlinx.android.synthetic.main.diagnosis_list.view.*

class DiagnosisList: RecyclerView.Adapter<DiagnosisList.ViewHolder>(){

    private var mArrayListDiagnosis: ArrayList<DiagnosisModel> = ArrayList()
    private var mOnClickListen : ClickListen? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.diagnosis_list, parent , false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView10.text = mArrayListDiagnosis[position].newDiagnosis
        holder.textView12.text = mArrayListDiagnosis[position].therapy
        holder.textView14.text = mArrayListDiagnosis[position].date

    }

    fun setOnClick(clickListen: ClickListen){
        mOnClickListen = clickListen
    }

    override fun getItemCount(): Int {
        return mArrayListDiagnosis.size
    }

    fun setData(arrayListDiagnosis: ArrayList<DiagnosisModel>){
        mArrayListDiagnosis = arrayListDiagnosis
        notifyDataSetChanged()
    }

    fun removeData(position: Int){
        mArrayListDiagnosis.remove(mArrayListDiagnosis[position])
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var textView10 = itemView.textView10 // Diagnosis
        var textView12 = itemView.textView12 // Therapy
        var textView14 = itemView.textView14 // Date
        var imageView9 = itemView.imageView9 // Date

        init {
            imageView9.setOnClickListener {
                mOnClickListen?.deleteClick(mArrayListDiagnosis[adapterPosition].id)
                removeData(adapterPosition)
            }
        }
    }

    interface ClickListen{
        fun deleteClick(id: Int)
    }

}