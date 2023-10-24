package com.tbse.coroutinepractice.screens.collatz

import app.cash.turbine.test
import com.tbse.coroutinepractice.TestDispatchers
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by toddsmith on 10/22/23.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CollatzViewModelTest {

    private lateinit var testDispatchers: TestDispatchers
    private lateinit var viewModel: CollatzViewModel

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        viewModel = CollatzViewModel(testDispatchers)
    }

    @Test
    fun `test the testFlow`() = runTest {
        viewModel.testFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test sequence for 16`() = runBlocking {
        viewModel.startingNumberFlow.emit(16)
        viewModel.sequenceFlow.test {
            val item = awaitItem()
            println("item is $item")
//            testDispatchers.testDispatcher.scheduler.advanceTimeBy(100L)
//            assert(awaitItem() == 16)
//            assert(awaitItem() == 8)
//            assert(awaitItem() == 4)
//            assert(awaitItem() == 2)
//            assert(awaitItem() == 1)
//            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test sequence for 17`() = runBlocking {
        viewModel.sequenceFlow.test {
            viewModel.startingNumberFlow.emit(17)
            awaitItem() // 1
            assert(awaitItem() == 17)
            assert(awaitItem() == 52)
            assert(awaitItem() == 26)
            assert(awaitItem() == 13)
            assert(awaitItem() == 40)
            assert(awaitItem() == 20)
            assert(awaitItem() == 10)
            assert(awaitItem() == 5)
            assert(awaitItem() == 16)
            assert(awaitItem() == 8)
            assert(awaitItem() == 4)
            assert(awaitItem() == 2)
            assert(awaitItem() == 1)
            cancelAndIgnoreRemainingEvents()
        }
    }
}