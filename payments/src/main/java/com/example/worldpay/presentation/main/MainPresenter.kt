package com.example.worldpay.presentation.main

import com.example.worldpay.domain.common.AUTHORIZE
import com.example.worldpay.domain.usecase.AuthorizePaymentUseCase
import com.example.worldpay.presentation.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(val authorizePaymentUseCase: AuthorizePaymentUseCase): BasePresenter<MainMvpView>() {
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
    fun submitDetail(cardNumber: String, cardHolderName:String, expiryMonth: String, expiryYear: String, cvv: String){
        authorizePaymentUseCase.createObservable()
    }
}