package com.example.zoran.exercise

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ExerciseCategoryViewModel : ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun exerciseCategoryList() {

        ApiRepository.exerciseCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<ExerciseCategoryModel>>() {

                override fun onSuccess(model: ArrayList<ExerciseCategoryModel>) {
                    response.postValue(NetworkResponse.SUCCESS.ExerciseCategoryList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}