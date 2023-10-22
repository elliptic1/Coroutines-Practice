package com.tbse.coroutinepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tbse.coroutinepractice.screens.collatz.CollatzScreen
import com.tbse.coroutinepractice.screens.collatz.CollatzScreenConfig

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CollatzScreen(config = CollatzScreenConfig("1"))

        }

    }
}
