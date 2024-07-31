package com.truyentd.moviecompose.presentation.screens.sample

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AmountScreenPreview() {
    AmountScreen()
}

@Composable
fun AmountScreen() {
    var enteredAmount by remember { mutableStateOf("") }
    var amountToSend by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = amountToSend, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = enteredAmount,
            onValueChange = { enteredAmount = it },
            label = { Text(text = "Enter amount") },
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { amountToSend = enteredAmount }) {
            Text(text = "Send Amount")
        }
    }
}

@Composable
fun MyScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit, // Send the 'started' analytics event
) {
    val scope = rememberCoroutineScope()
    // Safely update the current lambdas when a new one is provided
    val currentOnStart by rememberUpdatedState(onStart)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            }
            scope.launch {  }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val value by loadNetworkImage(url = "def")

    /* Home screen content */
}

@Composable
fun HomeScreen() {
    DisposableEffect(key1 = key) {
        onDispose {
            // Clean up
        }
    }

    /* Home screen content */
}


data class Image(val id: Int = 0)

@Composable
fun CountdownTimer(initialTime: Int) {
    // Produce a state that counts down from the initialTime
    val timeLeft by produceState(initialValue = initialTime) {
        for (i in initialTime downTo 0) {
            value = i
            delay(1000L)
        }
    }

    SideEffect {

    }

    Log.d("ComposableWithSideEffect", "Composed with name = anc")

    // UI that displays the time left
    Text(text = "Time left: $timeLeft seconds")
}


@Composable
fun fetchDataFromNetwork(): State<String> {
    return produceState(initialValue = "Loading...") {
        delay(1000) // Simulate network call delay
        value = "Data Loaded"
    }
}

data class Message(val id: Int)

@Composable
fun MessageList(messages: List<Message>) {
    Box {
        val listState = rememberLazyListState()
        // Show the button if the first visible item is past
        // the first item. We use a remembered derived state to
        // minimize unnecessary compositions
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        val key = 0;

        LaunchedEffect(key1 = key) {
            // Perform a side effect
        }

        LazyColumn(state = listState) {
            // ...
        }
        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton()
        }
    }
}

@Composable
fun ScrollToTopButton() {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            // ...
        }

        // Show the button if the first visible item is past
        // the first item. We use a remembered derived state to
        // minimize unnecessary compositions
        val showButton by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        AnimatedVisibility(visible = showButton) {
            ScrollToTopButton()
        }
    }
}

val key = 1;

@Composable
fun ListScreen() {
    val listState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
}


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean = false,
) {
    val currentIsLoggedIn by rememberUpdatedState(newValue = isLoggedIn)
    LaunchedEffect(key1 = null) {
        delay(2000L)
        if (currentIsLoggedIn) {
            goToTopScreen()
        } else {
            goToLoginScreen()
        }
    }

    /* Splash screen content */
}

fun goToTopScreen() {}
fun goToLoginScreen() {}


@Composable
fun TestScreen() {
    SideEffect {
        analytics.sendEvent("Open screen")
    }

    /* MyScreen content*
}

