package com.tbse.coroutinepractice.screens.collatz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow


/**
 * Created by toddsmith on 10/21/23.
 */
class CollatzViewModel : ViewModel() {

    val startingNumberFlow = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val countDownFlow = startingNumberFlow.flatMapLatest {
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
        }

    }

}
