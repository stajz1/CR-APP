package com.example.zoran.fragmentStaffSide

import com.google.gson.annotations.SerializedName

data class AssignedExerciseModel(
    val exercise: Exercise
)

data class Exercise(
    @SerializedName("_id")
    val id: Int,
    val exerciseName: String,
    val image1Name: String,
    val image2Name: String,
    val exerciseDetail: String
)