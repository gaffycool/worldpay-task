package com.example.worldpay.domain.usercase

import com.example.worldpay.RxImmediateSchedulerRule
import com.example.worldpay.domain.common.*
import com.example.worldpay.domain.entity.AuthRequest
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import com.example.worldpay.domain.usecase.AuthorizePaymentUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

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
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        authorizePaymentUseCase = AuthorizePaymentUseCase(paymentRepository)
    }

    @Test
    fun testAuthorizePaymentFailureWithCardNoRequired() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()

        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = ""
        data[CARD_HOLDER_NAME]=""
        data[EXPIRY_MONTH]=""
        data[EXPIRY_YEAR]=""
        data[CVV]=""
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(500, testSubscriber.values()[0].statusCode)
        assertEquals(CARD_NO_REQUIRED, testSubscriber.values()[0].message)
    }

    @Test
    fun testAuthorizePaymentFailureWithCardHolderNameRequired() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()

        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = "1234123412341234"
        data[CARD_HOLDER_NAME]=""
        data[EXPIRY_MONTH]=""
        data[EXPIRY_YEAR]=""
        data[CVV]=""
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(500, testSubscriber.values()[0].statusCode)
        assertEquals(CARD_HOLDER_NAME_REQUIRED, testSubscriber.values()[0].message)
    }

    @Test
    fun testAuthorizePaymentFailureWithExpiryMonthRequired() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()

        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = "1234123412341234"
        data[CARD_HOLDER_NAME]="Gaffar"
        data[EXPIRY_MONTH]=""
        data[EXPIRY_YEAR]=""
        data[CVV]=""
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(500, testSubscriber.values()[0].statusCode)
        assertEquals(EXPIRY_MONTH_REQUIRED, testSubscriber.values()[0].message)
    }

    @Test
    fun testAuthorizePaymentFailureWithExpiryYearRequired() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()

        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = "1234123412341234"
        data[CARD_HOLDER_NAME]="Gaffar"
        data[EXPIRY_MONTH]="10"
        data[EXPIRY_YEAR]=""
        data[CVV]=""
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(500, testSubscriber.values()[0].statusCode)
        assertEquals(EXPIRY_YEAR_REQUIRED, testSubscriber.values()[0].message)
    }

    @Test
    fun testAuthorizePaymentFailureWithCVVRequired() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()

        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = "1234123412341234"
        data[CARD_HOLDER_NAME]="Gaffar"
        data[EXPIRY_MONTH]="10"
        data[EXPIRY_YEAR]="24"
        data[CVV]=""
        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(500, testSubscriber.values()[0].statusCode)
        assertEquals(CVV_REQUIRED, testSubscriber.values()[0].message)
    }

    @Test
    fun testAuthorizePaymentSuccess() {
        val testSubscriber: TestObserver<AuthResponse> = TestObserver()
        val authResponse = AuthResponse(200,"Success")
        val data = mutableMapOf<String, Any>()
        data[CARD_NO] = "1234123412341234"
        data[CARD_HOLDER_NAME]="Gaffar"
        data[EXPIRY_MONTH]="10"
        data[EXPIRY_YEAR]="24"
        data[CVV]="330"
        val authRequest = AuthRequest("1234123412341234","Gaffar",10,24,330)
        `when`(paymentRepository.authorizePayment(authRequest)).thenReturn(Single.create {
            it.onSuccess(authResponse)
        })

        val result = authorizePaymentUseCase.createObservable(data)
        result.subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        assertEquals(200, testSubscriber.values()[0].statusCode)
    }

}