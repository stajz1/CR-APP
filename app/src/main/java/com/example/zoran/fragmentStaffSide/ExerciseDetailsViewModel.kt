package com.example.zoran.fragmentStaffSide

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ExerciseDetailsViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun exerciseDetails(id: Int) {
        ApiRepository.exerciseDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Exercise>() {

                override fun onSuccess(model: Exercise) {
                    response.postValue(NetworkResponse.SUCCESS.ExerciseDetails(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

    fun exerciseAssign(exerciseId: Int, patientId: Int){
        ApiRepository.assignExercise(exerciseId, patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                    response.postValue(NetworkResponse.SUCCESS.AssignExercise(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

    fun deleteAssignedExercise(exerciseId: Int, patientId: Int){
        Log.e("DeleteExerciseId", exerciseId.toString())
        Log.e("DeleteExerciseId", patientId.toString())
        ApiRepository.deleteAssignedExercise(exerciseId, patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                    response.postValue(NetworkResponse.SUCCESS.DeletePatientExercise(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}