package com.tbse.coroutinepractice.screens.collatz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

/**
 * Created by toddsmith on 10/22/23.
 */

@Composable
fun CollatzScreen(
    modifier: Modifier = Modifier,
    config: CollatzScreenConfig,
) {

    val collatzViewModel: CollatzViewModel = viewModel()
    val time = collatzViewModel.sequenceFlow.collectAsState(0).value

    var textState by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Collatz Conjecture",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = time.toString(),
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        TextField(
            value = textState,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            onValueChange = {
                textState = it
            },
        )
        Button(
            onClick = {
                collatzViewModel.viewModelScope.launch {
                    collatzViewModel.startingNumberFlow.emit(textState.toIntOrNull() ?: 1)
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Start")
        }
    }
}

data class CollatzScreenConfig(
    val a: String,
)

@Preview(showBackground = true)
@Composable
fun CollatzScreenPreview(
    @PreviewParameter(CollatzScreenConfigProvider::class)
    config: CollatzScreenConfig,
) {
    CollatzScreen(
        modifier = Modifier,
        config = config,
    )
}

class CollatzScreenConfigProvider :
    PreviewParameterProvider<CollatzScreenConfig> {
    override val values: Sequence<CollatzScreenConfig>
        get() = sequenceOf(
            CollatzScreenConfig(
                a = "123",
            )
        )
}