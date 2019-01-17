package com.example.worldpay.presentation.base

interface Presenter<T: MvpView>{
    fun attachView(view:T)
    fun detachView()
    fun isViewAttached():Boolean
}