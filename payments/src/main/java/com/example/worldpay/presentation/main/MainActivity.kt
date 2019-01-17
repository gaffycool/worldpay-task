package com.example.worldpay.presentation.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.worldpay.R
import com.example.worldpay.domain.common.TRANSACTION_TYPE
import com.example.worldpay.presentation.base.BaseActivity
import com.example.worldpay.presentation.main.authorizepayment.AuthorizePaymentFragment

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMvpView {

    override fun getContentView() = R.layout.activity_main
    val FRAGMENT_AUTHORIZE = 0

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
        mainPresenter.attachView(this)
        mainPresenter.loadScreen(intent.getStringExtra(TRANSACTION_TYPE))
        setSupportActionBar(toolbar)
    }


    override fun onDestroy() {
        mainPresenter.detachView()
        super.onDestroy()
    }

    override fun displayAuthorizationScreen() {
        displayFragment(FRAGMENT_AUTHORIZE)
    }
    fun displayFragment(fragmentType: Int){
        var fragment = Fragment()
        when(fragmentType){
            FRAGMENT_AUTHORIZE->{
                fragment = AuthorizePaymentFragment()
            }
        }
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}
