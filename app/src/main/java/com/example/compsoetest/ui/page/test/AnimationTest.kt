package com.example.compsoetest.ui.page.test

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compsoetest.ui.theme.background
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

const val NUMBER = 80
val ColorDone = Color(0xFF89E8FF)

val ColorDone2 = Color(0xFF6CB1C2)
@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun Movement() {

    val coroutineScope = rememberCoroutineScope()

    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }

    val offset2 = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }



    val configuration = LocalConfiguration.current

    val screenHeight = with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx() }
    val screenWidth = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }

    val offsetList = remember {
        MutableList(NUMBER) {Animatable(Offset(screenWidth / 2, screenHeight / 2), Offset.VectorConverter) }.toList()
    }

    fun randomOffset() {
        offsetList.forEach {
            coroutineScope.launch {
                it.animateTo(
                    Offset(
                        Random.nextDouble(0f.toDouble(), screenWidth.toDouble()).toFloat(),
                        Random.nextDouble(0f.toDouble(), screenHeight.toDouble()).toFloat()
                    ),
                    spring(Spring.DampingRatioHighBouncy, 5f)
                )
            }
        }
        coroutineScope.launch {
            offset.animateTo(
                Offset(
                    Random.nextDouble(0f.toDouble(), screenWidth.toDouble()).toFloat(),
                    Random.nextDouble(0f.toDouble(), screenHeight.toDouble()).toFloat()
                ),
                spring(Spring.DampingRatioHighBouncy, 15f)
            )

        }
        coroutineScope.launch {
            offset2.animateTo(
                Offset(
                    Random.nextDouble(0f.toDouble(), screenWidth.toDouble()).toFloat(),
                    Random.nextDouble(0f.toDouble(), screenHeight.toDouble()).toFloat()
                ),
                spring(Spring.DampingRatioHighBouncy, 15f)
            )
        }
    }

    var sec by remember {
        mutableStateOf(3)
    }

    val isSleeping by remember {
        derivedStateOf {
            sec > 5
        }
    }


    var checkState by remember {
        mutableStateOf(true)
    }


    fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

    val randomValue = { (0..255).random() }

    var color by remember {
        mutableStateOf(Color.DarkGray)
    }

    var color2 by remember {
        mutableStateOf(Color.DarkGray)
    }


    val colorList = remember {
        MutableList(NUMBER) { mutableStateOf(Color.DarkGray) }.toList()
//        listOf(
//            mutableStateOf(Color.DarkGray),
//            mutableStateOf(Color.DarkGray),
//            mutableStateOf(Color.DarkGray),
//            mutableStateOf(Color.DarkGray)
//        )
    }

    fun newColors(): Color {
        return when ((1..3).random()) {
            1 -> ColorDone2
            2 -> Color(140,140,140)
            3 -> Color(80, 80, 80)
            else -> ColorDone
        }
    }

    fun randomColor() {
        colorList.forEach { it.value = newColors() }
//        color = Color(randomValue(), randomValue(), randomValue())
//        color2 = Color(randomValue(), randomValue(), randomValue())
    }


    val animatedColor by animateColorAsState(
        targetValue = color,
        spring(Spring.DampingRatioHighBouncy, 15f)
    )

    val animatedColor2 by animateColorAsState(
        targetValue = color2,
        spring(Spring.DampingRatioHighBouncy, 15f)
    )


    var size by remember {
        mutableStateOf(36.dp)
    }

    var size2 by remember {
        mutableStateOf(36.dp)
    }

    val sizeList = remember {
        MutableList(NUMBER) { mutableStateOf(36.dp) }.toList()
//        listOf(
//            mutableStateOf(36.dp),
//            mutableStateOf(36.dp),
//            mutableStateOf(36.dp),
//            mutableStateOf(36.dp)
//        )
    }


    val randomSize = {
        sizeList.forEach { it.value = (36..108).random().dp }
        size = (36..108).random().dp
        size2 = (36..108).random().dp
    }

    val animatedSize by animateDpAsState(
        targetValue = size,
        spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)
    )

    val animatedSize2 by animateDpAsState(
        targetValue = size2,
        spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)
    )

    Box(modifier = Modifier
        .background(background)
        .fillMaxSize()
        .pointerInput(Unit) {
            coroutineScope {
                while (true) {
                    val position = awaitPointerEventScope {

                        awaitFirstDown().position
                    }
//                    val position = awaitPointerEventScope {
//                        awaitFirstDown().position
//                    }
                    Log.d("!!", "Movement: $position")
                    launch {
                        offset.animateTo(
                            position,
                            spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow)
                        )
                    }
                }
            }
        }
        .pointerInteropFilter {
            if (it.action == MotionEvent.ACTION_DOWN) {
                randomColor()
                randomSize()
                sec = 0
            }
            false
        }) {

        if (checkState) {
            Column(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(36.dp)
                    .width(100.dp)
            ) {
                Text(text = "X = " + offset2.value.x.toString(), textAlign = TextAlign.Left)
                Text(text = "Y = " + offset2.value.y.toString(), textAlign = TextAlign.Left)
                Text(text = "Size = " + animatedSize.value.toInt().toString())
            }
        }

//        Spacer(
//            modifier = Modifier
//                .offset { offset.value.toIntOffset() }
//                .clip(RoundedCornerShape(100))
//                .size(animatedSize)
//                .background(animatedColor)
//                .clickable { checkState = !checkState }
//        )
//
//        Spacer(
//            modifier = Modifier
//                .offset { offset2.value.toIntOffset() }
//                .clip(RoundedCornerShape(100))
//                .size(animatedSize2)
//                .background(animatedColor2)
//                .clickable { checkState = !checkState }
//        )

        repeat(NUMBER) {

            val animatedSizeIn by animateDpAsState(
                targetValue = sizeList[it].value,
                spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)
            )

            val animatedColorIn by animateColorAsState(
                targetValue = colorList[it].value,
                spring(Spring.DampingRatioHighBouncy, 15f)
            )

            val padding = if (animatedSizeIn > 0.dp) animatedSizeIn else 0.dp

            Spacer(
                modifier = Modifier
                    .padding(end = padding, bottom = padding)
                    .offset { offsetList[it].value.toIntOffset() }
                    .clip(RoundedCornerShape(100))
                    .size(animatedSizeIn)
                    .background(animatedColorIn)
//                    .clickable { checkState = !checkState }
            )
        }
    }



    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            while (true) {
//                delay(1000)
//                sec += 1
//                if (isSleeping) {
//                    randomOffset()
//                    delay((150..500).random().toLong())
//                    randomOffset()
//                    delay((150..500).random().toLong())
//                    randomOffset()
//                    sec = 0
//                    randomColor()
//                    randomSize()
//                }

                delay(400)
                randomOffset()
//                    sec = 0
                randomColor()
                randomSize()

            }
        }
    }

    fun circularMotion() {

    }
}
