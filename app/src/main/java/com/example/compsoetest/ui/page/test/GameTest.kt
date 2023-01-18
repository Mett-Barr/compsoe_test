package com.example.compsoetest.ui.page.test

import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Math.pow
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameTest() {

//    var times by remember {
//        mutableStateOf(0)
//    }
//    val duration = (1000 * 0.9.pow(times.toDouble())).toLong()
////    val duration = 500L
//
//
//
//    var sec by remember {
//        mutableStateOf(0)
//    }


    var isLeaving by remember {
        mutableStateOf(true)
    }


    /** --------------------------------------------- */
    val scope = rememberCoroutineScope()

    var number by remember {
        mutableStateOf(0)
    }

    val duration by remember {
        derivedStateOf { (2000 * 0.8.pow(number.toDouble())).toLong() }
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
            isLeaving = false
            reStart()
            timer()
        } else {
            timer()
        }
    }

    /** --------------------------------------------- */


    val configuration = LocalConfiguration.current

    val screenHeight = with(LocalDensity.current) { (configuration.screenHeightDp - 100).dp.toPx() }
    val screenWidth = with(LocalDensity.current) { (configuration.screenWidthDp - 100).dp.toPx() }


    var offset by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    fun positionChange() {
        offset = Offset(
            Random.nextDouble(0f.toDouble(), screenWidth.toDouble()).toFloat(),
            Random.nextDouble(0f.toDouble(), screenHeight.toDouble()).toFloat()
//            Random.nextDouble(1500f.toDouble(), screenWidth.toDouble()).toFloat(),
//            Random.nextDouble(800f.toDouble(), screenHeight.toDouble()).toFloat()
        )

//        Log.d("!!", "positionChange: $offset")
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            timer()
        }
        positionChange()
    }


    fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

//    val timer = {
//        LaunchedEffect(key1 = Unit) {
//            coroutineScope.launch {
//                delay(3000)
//                isLeaving = false
//            }
//        }
//    }


    var color by remember {
        mutableStateOf(Color.DarkGray)
    }

    val randomValue = { (0..255).random() }

    fun randomColor() {
        color = Color(randomValue(), randomValue(), randomValue())
    }


//    fun die() = coroutineScope.launch {
////        delay(duration)
//        repeat(50) {
//            delay(200)
//            sec++
//        }
//        isLeaving = false
//    }
//
//    var job: Job? = null
//
//
//    val timer = object : CountDownTimer(duration,0) {
//        override fun onTick(p0: Long) {
////            TODO("Not yet implemented")
//        }
//
//        override fun onFinish() {
//            isLeaving = false
//        }
//
//    }


    val scale by animateFloatAsState(
        targetValue = if (isLeaving) 1f else 0f,
        finishedListener = {
            if (it == 0f) {
                randomColor()
                positionChange()
                isLeaving = true

                reStart()
            }
            if (it == 1f) {

                reStart()
//                isLeaving = false
            }
        },
//        animationSpec = tween(500)
    )




    Box(
        modifier = Modifier
//        .background(Color(40, 40, 40))
            .fillMaxSize()
    ) {


        Spacer(modifier = Modifier
            .offset { offset.toIntOffset() }
//            .offset(1000.dp, 500.dp)
            .scale(scale)
            .clip(RoundedCornerShape(100))
            .alpha(scale)
            .background(color)
            .size(50.dp)
            .pointerInteropFilter {
                if (it.action == MotionEvent.ACTION_DOWN) {
                    if (isLeaving) {
                        if (duration >= 250 ) number++
                        isLeaving = false
                        reStart()
                    }
                }
                true
            }
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onTap = {
//                        if (isLeaving) {
//                            times += 1
//                            isLeaving = false
//                            coroutineScope.cancel()
//                        }
//                    }
//                )
//            }
//            .clickable(isLeaving) {
//                times += 1
//                isLeaving = false
//                coroutineScope.cancel()
//            }
        )

        Text(
            text = number.toString() + "\n" +
                    isLeaving.toString(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .width(100.dp)
        )
    }

//    LaunchedEffect(key1 = Unit) {
//        coroutineScope.launch {
//            delay(1000)
//            die()
////            isLeaving = false
//
////            times ++
//        }
//    }
}