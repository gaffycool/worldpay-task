package com.example.worldpay.domain.usecase

import io.reactivex.Observable

/**
 * Also known as interactors, use cases encapsulate a single, very specific task that can be performed.
 */
abstract class UseCase<T> {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null): Observable<T> = createObservable(withData)
}