package com.example.zoran.network

import android.util.Log
import com.example.zoran.auth.AuthModel
import com.example.zoran.diagnosis.DiagnosisModel
import com.example.zoran.exercise.ExerciseCategoryModel
import com.example.zoran.fragmentPatientSide.HistoryListModel
import com.example.zoran.fragmentPatientSide.VitalSignsListModel
import com.example.zoran.fragmentStaffSide.AssignedExerciseModel
import com.example.zoran.fragmentStaffSide.Exercise
import com.example.zoran.fragmentStaffSide.PatientListModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiRepository {
    private val apiService: ApiService

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://crcoachathome.com/api/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    fun signIn(mobileNo: String, password: String, userType: String): Single<AuthModel> {
        return apiService.signIn(mobileNo, password, userType)
    }

    fun patientList(): Single<ArrayList<PatientListModel>> {
        return apiService.patientList()
    }

    fun assignedExerciseList(id: Int): Single<ArrayList<AssignedExerciseModel>> {
        return apiService.getAssignedExerciseList(id)
    }

    fun exerciseList(id: Int): Single<ArrayList<Exercise>> {
        return apiService.getExerciseList(id)
    }

    fun exerciseCategoryList(): Single<ArrayList<ExerciseCategoryModel>> {
        return apiService.exerciseCategoryList()
    }

    fun exerciseDetails(id: Int): Single<Exercise> {
        return apiService.exerciseDetail(id)
    }

    fun assignExercise(exerciseId: Int, patientId: Int): Single<StoreDataResponseModel> {
        Log.e("exerciseId", exerciseId.toString())
        Log.e("exerciseId", patientId.toString())
        return apiService.assignExercise(exerciseId, patientId)
    }

    fun deleteAssignedExercise(exerciseId: Int, patientId: Int): Single<StoreDataResponseModel> {
        return apiService.deleteAssignedExercise(exerciseId, patientId)
    }

    fun deleteDiagnosis(id: Int): Single<StoreDataResponseModel> {
        return apiService.deleteDiagnosis(id)
    }

    fun storeDiagnosis(patientId: Int, newDiagnosis: String, date: String, therapy: String): Single<StoreDataResponseModel> {
        return apiService.storeDiagnosis(patientId, newDiagnosis, date, therapy)
    }

    fun diagnosisList(patientId: Int): Single<ArrayList<DiagnosisModel>> {
        return apiService.getDiagnosisList(patientId)
    }

    fun storeVitalSigns(patientId: Int, vitalSigns: String, value: String, maxValue: String): Single<StoreDataResponseModel> {
        return apiService.storeVitalSigns(patientId, vitalSigns, value, maxValue)
    }

    fun vitalSignsList(patientId: Int): Single<ArrayList<VitalSignsListModel>> {
        return apiService.getVitalSignsList(patientId)
    }

    fun addWorkOut(exerciseId: Int, steps: Int, patientId: Int): Single<StoreDataResponseModel>{
        return apiService.addWorkOut(exerciseId, steps, patientId)
    }

    fun workOutList(patientId: Int): Single<ArrayList<HistoryListModel>>{
        return apiService.historyList(patientId)
    }

}