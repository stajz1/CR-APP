package com.example.zoran.exercise

import com.google.gson.annotations.SerializedName

data class ExerciseCategoryModel (
    val categoryImage: String?,
    val categoryName: String,
    @SerializedName("_id")
    val id: Int
)