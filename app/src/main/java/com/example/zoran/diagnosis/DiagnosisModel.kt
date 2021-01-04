package com.example.zoran.diagnosis

import com.google.gson.annotations.SerializedName

data class DiagnosisModel(
    @SerializedName("_id")
    val id: Int,

    val newDiagnosis: String,
    val date: String,
    val therapy: String
)