package com.example.compsoetest.ui.page.test

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.pow

@Preview
@Composable
fun Timer() {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var number by remember {
        mutableStateOf(0)
    }

    val duration by remember {
        derivedStateOf { (1000 * 0.9.pow(number.toDouble())).toLong() }
    }

    fun currentTime(): Long = Calendar.getInstance().timeInMillis

    var endTime by remember {
        mutableStateOf(currentTime() + duration)
    }

    fun reStart() {
        endTime = currentTime()
    }


    suspend fun timer() {
        delay(duration)
        if (currentTime() >= endTime + duration) {
            reStart()
            timer()
        } else {
            timer()
        }
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            timer()
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
        .clickable {
            if (duration >= 500L) number ++
            reStart()
        }) {
        Text(text = duration.toString(), modifier = Modifier.align(Alignment.Center))
    }

//    var number by remember {
//        mutableStateOf(0) }
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
//
//    var isRunning = true
//
//    fun getTimer(): CountDownTimer = object : CountDownTimer(10000, 1000) {
//        override fun onTick(p0: Long) {
//            number++
//        }
//
//        override fun onFinish() {
//            number = 0
//            isRunning = false
//        }
//    }
//
//    var timer = getTimer().start()
//


//    Box(modifier = Modifier
//        .clickable {
//            if (isRunning) {
//                timer.cancel()
//                timer = getTimer()
//            } else timer.start()
//            isRunning = !isRunning
//        }
//        .fillMaxSize()) {
//        Text(
//            number.toString(),
//            modifier = Modifier
////                .clickable { timer.cancel() }
//                .align(Alignment.Center),
//            style = TextStyle(fontSize = 36.sp)
//        )
//    }
}