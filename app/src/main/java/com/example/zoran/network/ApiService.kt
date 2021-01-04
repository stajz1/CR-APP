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
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    fun signIn(@Field("userName") mobile: String?, @Field("password") password: String?, @Field("userType") userType: String?): Single<AuthModel>

    @FormUrlEncoded
    @POST("exercise/assignExercise")
    fun assignExercise(@Field("exerciseId") exerciseId: Int?, @Field("userId") userId: Int?): Single<StoreDataResponseModel>

//    @FormUrlEncoded
//    @POST("exercise/assignExercise")
//    fun deleteAssignedExercise(@Field("exerciseId") exerciseId: Int?, @Field("userId") userId: Int?): Single<StoreDataResponseModel>

    @FormUrlEncoded
    @POST("patient/workOut")
    fun addWorkOut(@Field("exerciseId") exerciseId: Int?,@Field("steps") steps: Int?, @Field("userId") userId: Int?): Single<StoreDataResponseModel>

    @GET("patient/history/{userId}")
    fun historyList(@Path("userId") userId: Int): Single<ArrayList<HistoryListModel>>

    @DELETE("diagnosis/diagnosisList/{diagnosisId}")
    fun deleteDiagnosis(@Path("diagnosisId") diagnosisId: Int): Single<StoreDataResponseModel>

    @DELETE("patient/deletePatientExercise/{exerciseId}/{userId}")
    fun deleteAssignedExercise(@Path("exerciseId") exerciseId: Int,@Path("userId") userId: Int): Single<StoreDataResponseModel>

    @GET("patient/list")
    fun patientList(): Single<ArrayList<PatientListModel>>

    @GET("exercise/category")
    fun exerciseCategoryList(): Single<ArrayList<ExerciseCategoryModel>>

    @GET("exercise/{exerciseId}")
    fun exerciseDetail(@Path("exerciseId") id: Int): Single<Exercise>

    @GET("exercise/fetchAssignedExercise/{id}")
    fun getAssignedExerciseList(@Path("id") id: Int): Single<ArrayList<AssignedExerciseModel>>

    @GET("exercise/category/{categoryId}")
    fun getExerciseList(@Path("categoryId") id: Int): Single<ArrayList<Exercise>>

    @GET("diagnosis/diagnosisList/{userId}")
    fun getDiagnosisList(@Path("userId") userId: Int): Single<ArrayList<DiagnosisModel>>

    @FormUrlEncoded
    @POST("diagnosis/storeDiagnosis")
    fun storeDiagnosis(@Field("userId") id: Int,@Field("newDiagnosis") newDiagnosis: String,@Field("date") date: String,@Field("therapy") therapy: String): Single<StoreDataResponseModel>

    @GET("vitalSign/vitalSignsList/{userId}")
    fun getVitalSignsList(@Path("userId") userId: Int): Single<ArrayList<VitalSignsListModel>>

    @FormUrlEncoded
    @POST("vitalSign/storeVitalSigns")
    fun storeVitalSigns(@Field("userId") id: Int,@Field("vitalSign") vitalSign: String,@Field("value") value: String,@Field("valueMax") valueMax: String): Single<StoreDataResponseModel>

}