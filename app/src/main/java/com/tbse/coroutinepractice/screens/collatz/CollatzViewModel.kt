package com.tbse.coroutinepractice.screens.collatz

import androidx.lifecycle.ViewModel
import com.tbse.coroutinepractice.DispatcherProvider
import com.tbse.coroutinepractice.DispatcherProviderImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn


/**
 * Created by toddsmith on 10/21/23.
 */
class CollatzViewModel(
    dispatchers: DispatcherProvider = DispatcherProviderImpl()
) : ViewModel() {

    val testFlow = flow {
        emit(1)
        emit(2)
    }

    val startingNumberFlow = MutableSharedFlow<Int>(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val sequenceFlow = startingNumberFlow.flatMapLatest {
        println("latest number is $it")
        var value = it
        flow {
            while (value > 1) {
                delay(100)
                emit(value)
                if (value % 2 == 0) {
                    value /= 2
                } else {
                    value = value * 3 + 1
                }
            }
            emit(value)
        }.flowOn(dispatchers.main)

    }

}
