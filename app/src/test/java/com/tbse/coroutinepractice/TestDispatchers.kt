package com.tbse.coroutinepractice

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

/**
 * Created by toddsmith on 10/22/23.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatchers : DispatcherProvider {
    val testDispatcher = StandardTestDispatcher()
    override val main: kotlinx.coroutines.CoroutineDispatcher
        get() = testDispatcher
    override val io: kotlinx.coroutines.CoroutineDispatcher
        get() = testDispatcher
    override val default: kotlinx.coroutines.CoroutineDispatcher
        get() = testDispatcher
}
