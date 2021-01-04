package com.example.zoran.fragmentStaffSide

import com.google.gson.annotations.SerializedName

data class PatientListModel (
    val fullName: String,
    val age: String,
    val diagnosis: String,
    @SerializedName("_id")
    val id: Int
)