package com.example.worldpay.presentation.main.authorizepayment

import com.example.worldpay.domain.common.*
import com.example.worldpay.domain.usecase.AuthorizePaymentUseCase
import com.example.worldpay.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthorizePaymentPresenter @Inject constructor(private val authorizePaymentUseCase: AuthorizePaymentUseCase): BasePresenter<AuthorizePaymentView>() {
    fun submitDetail(cardNumber: String, cardHolderName:String, expiryMonth: String, expiryYear: String, cvv: String){
        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = cardNumber
        data[CARD_HOLDER_NAME]=cardHolderName
        data[EXPIRY_MONTH]=expiryMonth
        data[EXPIRY_YEAR]=expiryYear
        data[CVV]=cvv
        authorizePaymentUseCase.createObservable(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.displayToast(it.message)
            },{
                view?.displayToast(it.localizedMessage)
            })
    }
}