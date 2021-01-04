package com.example.zoran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.fragmentStaffSide.PatientListModel
import kotlinx.android.synthetic.main.patient_list.view.*

class PatientList: RecyclerView.Adapter<PatientList.ViewHolder>(){

    var mOnClick : onClick? = null
    var mPatientList : ArrayList<PatientListModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.patient_list, parent , false))

    fun setOnClick(onClick: onClick){
        mOnClick = onClick
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fullName.text = mPatientList[position].fullName

        if (mPatientList[position].age != null){
            holder.age.text = "(Alter ".plus(mPatientList[position].age).plus(")")
        }

        if (mPatientList[position].diagnosis != null){
            holder.diagnosis.text = mPatientList[position].diagnosis
        }

        holder.id.text = mPatientList[position].id.toString()

    }

    override fun getItemCount(): Int {
        return mPatientList.size
    }

    fun setData(patientList : ArrayList<PatientListModel>){
        mPatientList = patientList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val cardView = itemView.cardView
        val fullName = itemView.textView10
        val age = itemView.textView11
        val diagnosis = itemView.textView12
        val id = itemView.textView14

        init {
            cardView.setOnClickListener {
                mOnClick?.cardClick(mPatientList[adapterPosition].fullName,mPatientList[adapterPosition].id ,mPatientList[adapterPosition].age, mPatientList[adapterPosition].diagnosis )
            }
        }

    }

    interface onClick{
        fun cardClick(fullName: String, id: Int, age: String, diagnosis: String)
    }

}