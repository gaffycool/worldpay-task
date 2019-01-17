package com.example.worldpay.domain.usercase

import com.example.worldpay.RxImmediateSchedulerRule
import com.example.worldpay.data.repository.PaymentRepositoryImpl
import com.example.worldpay.domain.common.PAYMENT_REQUEST
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import com.example.worldpay.domain.usecase.AuthorizePaymentUseCase
import com.example.worldpay.presentation.entity.PaymentDetail
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import javax.inject.Inject

import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RunWith(MockitoJUnitRunner::class)
class AuthorizePaymentTest {
    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val testRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var paymentRepository: PaymentRepository

    lateinit var authorizePaymentUseCase: AuthorizePaymentUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        authorizePaymentUseCase = AuthorizePaymentUseCase(paymentRepository)
    }

    @Test
    fun testAuthorizePaymentSuccess(){
        val testSubscriber:TestObserver<AuthResponse> = TestObserver()

        val authResponse =  AuthResponse(200,"Success")
        val paymentDetail = PaymentDetail("1234123412341234","Gaffar Akhtar",10,24,310)
        `when`(paymentRepository.authorizePayment(paymentDetail.toAuthRequest())).thenReturn(Single.create {
            it.onSuccess(authResponse)})

        val data = mutableMapOf<String,Any>()
        data[PAYMENT_REQUEST] = paymentDetail
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }

}