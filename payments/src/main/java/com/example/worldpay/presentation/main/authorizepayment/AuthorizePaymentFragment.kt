package com.example.worldpay.presentation.main.authorizepayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.worldpay.R
import com.example.worldpay.presentation.base.BaseFragment

class AuthorizePaymentFragment: BaseFragment() {
    private var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView ==null){
            rootView = inflater.inflate(R.layout.fragment_authorize_payment,container,false)
        }
        return rootView
    }
}