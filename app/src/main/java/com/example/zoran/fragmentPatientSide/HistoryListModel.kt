package com.example.zoran.fragmentPatientSide

import com.example.zoran.fragmentStaffSide.Exercise

data class HistoryListModel (

    val exercise: Exercise,
    val steps: String,
    val createdAt: String

)