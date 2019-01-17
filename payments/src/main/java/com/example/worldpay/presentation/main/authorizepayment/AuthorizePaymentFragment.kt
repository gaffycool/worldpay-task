package com.example.worldpay.presentation.main.authorizepayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.worldpay.R
import com.example.worldpay.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_authorize_payment.*
import javax.inject.Inject

class AuthorizePaymentFragment: BaseFragment(), AuthorizePaymentView {


    private var rootView: View? = null

    @Inject
    lateinit var presenter: AuthorizePaymentPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView ==null){
            rootView = inflater.inflate(R.layout.fragment_authorize_payment,container,false)
        }
        getComponent().inject(this)
        presenter.attachView(this)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmit.setOnClickListener {
            presenter.submitDetail(etCardNumber.text.toString(),etCardHolderName.text.toString(),
                etExpiryMonth.text.toString(), etExpiryYear.text.toString(),
                etCVV.text.toString())
        }
    }
    override fun displayToast(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
}