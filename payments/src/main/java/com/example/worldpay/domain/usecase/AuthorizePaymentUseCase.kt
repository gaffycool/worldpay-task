package com.example.worldpay.domain.usecase

import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import io.reactivex.Observable
import javax.inject.Inject

class AuthorizePaymentUseCase @Inject constructor(private val paymentRepo: PaymentRepository) :
    UseCase<AuthResponse>() {
    override fun createObservable(data: Map<String, Any>?): Observable<AuthResponse> {

        return Observable.empty()
    }
}