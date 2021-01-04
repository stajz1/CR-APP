package com.example.zoran.fragmentPatientSide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.exercise.ExerciseCategoryModel
import com.example.zoran.fragmentStaffSide.StoreDataResponseModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PatientWorkOutViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun addWorkOut(exerciseId: Int, steps:Int, patientId: Int) {

        ApiRepository.addWorkOut(exerciseId, steps, patientId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<StoreDataResponseModel>() {

                override fun onSuccess(model: StoreDataResponseModel) {
                    response.postValue(NetworkResponse.SUCCESS.AddWorkOut(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}