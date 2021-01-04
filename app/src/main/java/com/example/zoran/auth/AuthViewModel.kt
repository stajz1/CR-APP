package com.example.zoran.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zoran.network.ApiRepository
import com.example.zoran.network.NetworkResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AuthViewModel: ViewModel() {

    var response: MutableLiveData<NetworkResponse> = MutableLiveData()

    fun signIn(mobileNo: String, password:String, userType: String) {
        ApiRepository.signIn(mobileNo , password, userType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AuthModel>() {

                override fun onSuccess(model: AuthModel) {
                    response.postValue(NetworkResponse.SUCCESS.Auth(model))
                }

                override fun onError(e: Throwable) {
                    response.postValue(NetworkResponse.ERROR(e))
                }

            })
    }

}