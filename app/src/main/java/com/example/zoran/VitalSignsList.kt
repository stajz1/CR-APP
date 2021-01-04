package com.example.zoran

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoran.fragmentPatientSide.VitalSignsListModel
import kotlinx.android.synthetic.main.vital_signs_list.view.*

class VitalSignsList(private var context: Context): RecyclerView.Adapter<VitalSignsList.ViewHolder>(){

    var mVitalSignsList: ArrayList<VitalSignsListModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.vital_signs_list, parent , false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.date.text = mVitalSignsList[position].createdAt
        holder.vitalSign.text = mVitalSignsList[position].vitalSign

        if (mVitalSignsList[position].vitalSign == "Puls"){
            holder.maxValue.text = "Max: ".plus(mVitalSignsList[position].valueMax)
        }else{
            holder.maxValue.text = mVitalSignsList[position].valueMax

        }

        if (isInteger(mVitalSignsList[position].value) && isInteger(mVitalSignsList[position].valueMax)){
            if (mVitalSignsList[position].value.toInt() > mVitalSignsList[position].valueMax.toInt()){
                holder.maxValue.setTextColor(context.resources.getColor(R.color.red))
                holder.value.setTextColor(context.resources.getColor(R.color.red))
            }
        }



        holder.value.text = mVitalSignsList[position].value

        if (mVitalSignsList[position].vitalSign == "Body temperature"){
            holder.value.text = mVitalSignsList[position].value + " °C"
        }
    }

    override fun getItemCount(): Int {
        return mVitalSignsList.size
    }

    fun setData(vitalSignsList: ArrayList<VitalSignsListModel>){
        mVitalSignsList = vitalSignsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var vitalSign = itemView.textView10
        var date = itemView.textView12
        var maxValue = itemView.textView14
        var value = itemView.imageView9
    }
}