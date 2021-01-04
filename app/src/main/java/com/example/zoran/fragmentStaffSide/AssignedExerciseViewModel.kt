package com.example.zoran.fragmentStaffSide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AssignedExerciseViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun assignedExerciseList(id: Int) {
        ApiRepository.assignedExerciseList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<AssignedExerciseModel>>() {

                override fun onSuccess(model: ArrayList<AssignedExerciseModel>) {
                    response.postValue(NetworkResponse.SUCCESS.AssignedExerciseList(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}