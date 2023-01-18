package com.example.compsoetest.ui.page.canvas

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Calendar


const val duration = 1000 * 60 * 1.5
//const val duration = 1000 * 60 * 1.5f

@OptIn(ExperimentalTextApi::class)
@Composable
fun C01() {

    Surface(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .alpha(0.5f)
    ) {
        var boo by remember {
            mutableStateOf(true)
        }
        val ff by animateFloatAsState(
            targetValue = if (boo) 1f else 0f,
            animationSpec = FloatTweenSpec(1000 * 60 * 20, 0, FastOutSlowInEasing)
//            animationSpec = tween(1000 * 60 * 2)
        )
        LaunchedEffect(Unit) {
//            delay(6000)
            boo = false
        }

        var fff by remember {
            mutableStateOf(1f)
        }
        val end = Calendar.getInstance().timeInMillis + duration
        Log.d(
            "!!!",
            (Calendar.getInstance().timeInMillis + duration).toString() + "  " + Calendar.getInstance().timeInMillis.toString()
        )
        LaunchedEffect(Unit) {
//            delay(6000)
            withContext(Dispatchers.Default) {
                while (true) {
                    val res = ((end - Calendar.getInstance().timeInMillis) / (duration)).toFloat()
                    fff = res
                    Log.d("!!!", res.toString())
                    delay(30)
                }
            }
        }

        val f = rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(1000), RepeatMode.Reverse
            )
        )

        val textM = rememberTextMeasurer()
        val textM2 = rememberTextMeasurer()
        Canvas(
            modifier = Modifier
                .wrapContentSize()
//        .size(300.dp)
                .width(300.dp)
                .height(300.dp)
//        .padding(20.dp)
//        .background(Color.Blue)
        ) {

            Log.d("!!!", ff.toString())

            val rect = Rect(
                size = Size(size.width * 2, size.height * 2),
                offset = Offset(size.width * f.value / 2 + size.width / 4, size.height / -2)
            )
//            val rect = Rect(size = size, offset = Offset(size.width * f.value, 0f))
            val path = Path().apply {
//            moveTo(0f, 0f)
                arcTo(rect, 0f, 180f, false)
                arcTo(rect, 180f, 270f, false)
            }

            drawText(
                textM2,
                "text\ntesttestsetsetset",
                style = TextStyle(color = Color.White),
                topLeft = Offset(size.width / 3, size.height / 3)
            )

//            drawPath(
//                path = path,
//                brush = Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))
//            )

            val gradient = Brush.radialGradient(
                listOf(Color.White, Color.Transparent),
                center = Offset(size.width * f.value / 2 + size.width / 4, size.height / -2),
                radius = size.width * 2
            )

            clipPath(path) {
//                drawRect(color = Color.White, topLeft = Offset(0f, 0f), size)
                drawCircle(
                    gradient,
//                    Color.White,
                    150.dp.toPx(), Offset(size.width / 2, size.height / 2)
                )
                drawText(
                    textM,
                    "text\ntesttestsetsetset",
                    style = TextStyle(color = Color.Black),
                    topLeft = Offset(size.width / 3, size.height / 3)
                )
            }


//            drawPath(
//                path = path,
//                brush = Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))
//                brush = gradient
//            )


        }

    }
}