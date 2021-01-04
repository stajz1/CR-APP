package com.example.zoran.network

import com.example.zoran.auth.AuthModel
import com.example.zoran.diagnosis.DiagnosisModel
import com.example.zoran.exercise.ExerciseCategoryModel
import com.example.zoran.fragmentPatientSide.HistoryListModel
import com.example.zoran.fragmentPatientSide.VitalSignsListModel
import com.example.zoran.fragmentStaffSide.AssignedExerciseModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import com.example.zoran.fragmentStaffSide.Exercise
import com.example.zoran.fragmentStaffSide.PatientListModel

sealed class NetworkResponse {

    data class ERROR(val error: Throwable) : NetworkResponse()

    sealed class SUCCESS<out T>(val  response: T) : NetworkResponse() {
        data class Auth(val res : AuthModel) : SUCCESS<AuthModel>(res)
        data class PatientList(val res : ArrayList<PatientListModel>) : SUCCESS<ArrayList<PatientListModel>>(res)
        data class AssignedExerciseList(val res : ArrayList<AssignedExerciseModel>) : SUCCESS<ArrayList<AssignedExerciseModel>>(res)
        data class ExerciseCategoryList(val res : ArrayList<ExerciseCategoryModel>) : SUCCESS<ArrayList<ExerciseCategoryModel>>(res)
        data class ExerciseList(val res : ArrayList<Exercise>) : SUCCESS<ArrayList<Exercise>>(res)
        data class ExerciseDetails(val res : Exercise) : SUCCESS<Exercise>(res)
        data class AssignExercise(val res : StoreDataResponseModel) : SUCCESS<StoreDataResponseModel>(res)
        data class AddDiagnosis(val res : StoreDataResponseModel) : SUCCESS<StoreDataResponseModel>(res)
        data class AddVitalSigns(val res : StoreDataResponseModel) : SUCCESS<StoreDataResponseModel>(res)
        data class AddWorkOut(val res : StoreDataResponseModel) : SUCCESS<StoreDataResponseModel>(res)
        data class DeletePatientExercise(val res : StoreDataResponseModel) : SUCCESS<StoreDataResponseModel>(res)
        data class DiagnosisList(val res :  ArrayList<DiagnosisModel>) : SUCCESS< ArrayList<DiagnosisModel>>(res)
        data class VitalSignsList(val res :  ArrayList<VitalSignsListModel>) : SUCCESS< ArrayList<VitalSignsListModel>>(res)
        data class HistoryList(val res :  ArrayList<HistoryListModel>) : SUCCESS< ArrayList<HistoryListModel>>(res)
    }
}