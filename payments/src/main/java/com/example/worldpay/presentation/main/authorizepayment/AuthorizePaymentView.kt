package com.example.worldpay.presentation.main.authorizepayment

import com.example.worldpay.presentation.base.MvpView

interface AuthorizePaymentView : MvpView{
    fun displayToast(message: String)
}