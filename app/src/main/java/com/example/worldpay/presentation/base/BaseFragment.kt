package com.example.worldpay.presentation.base

import androidx.fragment.app.Fragment
import com.example.worldpay.MainApplication
import com.example.worldpay.presentation.di.component.DaggerFragmentComponent
import com.example.worldpay.presentation.di.component.FragmentComponent

open class BaseFragment: Fragment(){
    fun getComponent(): FragmentComponent {
        return  DaggerFragmentComponent.builder()
                .applicationComponent(MainApplication.get(context!!).applicationComponent)
                .build()
    }
}