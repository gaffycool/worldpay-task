package com.example.worldpay.domain.usecase

import com.example.worldpay.domain.common.CARD_NO
import com.example.worldpay.domain.common.CARD_NO_REQUIRED
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AuthorizePaymentUseCase @Inject constructor(private val paymentRepo: PaymentRepository) :
    UseCase<AuthResponse>() {
    override fun createObservable(data: Map<String, Any>?): Observable<AuthResponse> {
        when{
            data?.get(CARD_NO)?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, CARD_NO_REQUIRED))
                }.toObservable()
            }
        }
        return Observable.empty()
    }
}