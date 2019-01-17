package com.example.worldpay.presentation.main

import com.example.worldpay.domain.common.AUTHORIZE
import com.example.worldpay.domain.usecase.AuthorizePaymentUseCase
import com.example.worldpay.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(): BasePresenter<MainMvpView>() {
    fun loadScreen(transactionType:String?) {
        when (transactionType) {
            AUTHORIZE -> {
                view?.displayAuthorizationScreen()
            }
            null -> {
                view?.displayAuthorizationScreen()
            }

            ""-> {
                view?.displayAuthorizationScreen()
            }
        }
    }

}