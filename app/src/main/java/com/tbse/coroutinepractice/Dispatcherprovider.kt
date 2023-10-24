package com.tbse.coroutinepractice

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by toddsmith on 10/22/23.
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Default
}
