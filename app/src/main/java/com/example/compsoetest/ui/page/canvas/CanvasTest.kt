package com.example.compsoetest.ui.page.canvas

import android.graphics.BlurMaskFilter
import android.graphics.RectF
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Calendar
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.roundToInt


const val duration = 1000 * 60 * 1.5
//const val duration = 1000 * 60 * 1.5f

const val fontSize = 108

@OptIn(ExperimentalTextApi::class, ExperimentalAnimationApi::class)
@Composable
fun C01() {


    Surface(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .alpha(0.5f)
    ) {

        val current = LocalDensity.current

        val pxValue: (Dp) -> Float = { with(current) { it.toPx() } }
        val dpValue: (Float) -> Dp = { with(current) { it.toDp() } }

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
//        Log.d(
//            "!!!",
//            (Calendar.getInstance().timeInMillis + duration).toString() + "  " + Calendar.getInstance().timeInMillis.toString()
//        )
        LaunchedEffect(Unit) {
//            delay(6000)
            withContext(Dispatchers.Default) {
                while (true) {
                    val res = ((end - Calendar.getInstance().timeInMillis) / (duration)).toFloat()
                    fff = res
//                    Log.d("!!!", res.toString())
                    delay(10)
                }
            }
        }

//        val f = remember { mutableStateOf(0.95f) }
        val f = rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(5000), RepeatMode.Reverse
            )
        )

        val number by remember {
            derivedStateOf {
                val n = (5 * f.value).toDouble().roundToInt()
                Log.d("!!!", n.toString())
                n
            }
        }

        val textM = rememberTextMeasurer()
        val textM2 = rememberTextMeasurer()

        val textLayoutResult: TextLayoutResult =
            textM.measure(
                text = AnnotatedString(number.toString()),
                style = TextStyle(fontSize = fontSize.sp)
            )
        val textSize = textLayoutResult.size

        Canvas(
            modifier = Modifier
                .wrapContentSize()
//        .size(300.dp)
                .width(300.dp)
                .height(300.dp)
//        .padding(20.dp)
//        .background(Color.Blue)
        ) {

//            Log.d("!!!", ff.toString())

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


            Paint()

        }


        Card(
            modifier = Modifier
                .wrapContentSize()
                .size(dpValue(600f)),
            elevation = 60.dp,
            shape = CircleShape,
            backgroundColor = Color.Gray
        ) {}

        Canvas(
            modifier = Modifier
                .wrapContentSize()
                .size(300.dp)
//                .fillMaxSize()
//                .width(300.dp)
//                .height(300.dp)
        ) {

            val biasX = size.center.x - 300f
            val biasY = size.center.y - 300f

            val rectF1 = RectF(0f + biasX, 0f + biasY, 600f + biasX, 600f + biasY)
            val rect1 = Rect(0f + biasX, 0f + biasY, 600f + biasX, 600f + biasY)
            val rectF2 = RectF(
                600f - 600 * f.value + biasX,
                -100f + biasY,
                1400f - 600 * f.value + biasX,
                700f + biasY
            )
            val rect2 = Rect(
                600f - 600 * f.value + biasX,
                -100f + biasY,
                1400f - 600 * f.value + biasX,
                700f + biasY
            )
            val range = { rectF2.centerX() - rectF1.centerX() }


            val rF3 = RectF(0f, 0f, 300f + 100 * f.value, 300f + 100 * f.value)

            val path = android.graphics.Path().apply {
//            moveTo(0f, 0f)
                arcTo(
                    rectF1,
                    -getAngle(400f, 300f, range()),
                    getAngle(400f, 300f, range()) * 2,
                    false
                )
//                        arcTo(rectF2, 180 - getAngle(300f, 400f, range(), true), 180 + getAngle(300f, 400f, range()), false)
//                        Log.d("!!!", "${180f - getAngle(400f, 300f, range())}  ${180f + getAngle(400f, 300f, range())}")

//                        arcTo(rF3, 350f, 50f)
            }

            val path2 = android.graphics.Path().apply {
                arcTo(
                    rectF2,
                    180 - getAngle(300f, 400f, range(), true),
                    2 * getAngle(300f, 400f, range()),
                    false
                )
            }

            val angel1 = 180 - getAngle(400f, 300f, range())
            val angel2 = getAngle(300f, 400f, range(), true)
            val path3 = android.graphics.Path().apply {
                arcTo(
                    rectF1,
                    180 - angel1,
                    2 * angel1,
                    false
                )
                arcTo(
                    rectF2,
                    180 + angel2,
                    -2 * angel2,
                    false
                )
//                        arcTo(
//                            rectF1,
//                            -getAngle(400f, 300f, range()),
//                            getAngle(400f, 300f, range()) * 2,
//                            false
//                        )
//                        arcTo(
//                            rectF2,
//                            180 - getAngle(300f, 400f, range(), true),
//                            2 * getAngle(300f, 400f, range()),
//                            false
//                        )
            }
            val path4 = Path().apply {
                arcTo(
                    rect1,
                    180 - angel1,
                    2 * angel1,
                    false
                )
                arcTo(
                    rect2,
                    180 + angel2,
                    -2 * angel2,
                    false
                )
            }



            drawIntoCanvas {
                it.nativeCanvas.apply {
                    val blurMask = BlurMaskFilter(
                        50f,
                        BlurMaskFilter.Blur.SOLID
                    )
//                    val radialGradient = android.graphics.RadialGradient(
//                        100f, 100f, 50f,
//                        intArrayOf(android.graphics.Color.WHITE, android.graphics.Color.BLACK),
//                        floatArrayOf(0f, 0.9f), android.graphics.Shader.TileMode.CLAMP
//                    )
                    val paint = Paint().asFrameworkPaint().apply {
//                        shader = radialGradient

                        maskFilter = blurMask
//                        color = 0xFFFFFFFF.toInt()
//                        setColor((Color.White.copy(alpha = 0.985f)).value.toLong())
//                        color = android.graphics.Color.BLACK
                        color = android.graphics.Color.WHITE
                        style = android.graphics.Paint.Style.FILL
                        isDither = true
                    }

//                    drawCircle(100f, 100f, 50f, paint)
//                    drawLine(0f, 0f, 300f, 300f, paint)
//                    drawRect(RectF(0f, 0f, 300f, 300f), paint)


//                    drawRect(rectF2, paint)
//
//                    drawRect(rF3, paint)


//                    drawPath(path2, paint)
//                    drawPath(path, paint)

                    drawPath(path3, paint)


                }


                val canvasWidth = size.width
                val canvasHeight = size.height


//                drawText(
//                    textMeasurer = textM,
//                    text = number.toString(),
//                    topLeft = Offset(
//                        (canvasWidth - textSize.width) / 2f,
//                        (canvasHeight - textSize.height) / 2f
//                    ),
//                    style = TextStyle(fontSize = fontSize.sp, color = Color.White),
//                )
//
//                clipPath(path4) {
//                    drawText(
//                        textMeasurer = textM2,
//                        text = number.toString(),
//                        topLeft = Offset(
//                            (canvasWidth - textSize.width) / 2f,
//                            (canvasHeight - textSize.height) / 2f
//                        ),
//                        style = TextStyle(fontSize = fontSize.sp)
//                    )
//                }
            }
        }

        AnimatedContent(targetState = number) {
            Canvas(
                modifier = Modifier
                    .wrapContentSize()
                    .size(300.dp)
//                .fillMaxSize()
//                .width(300.dp)
//                .height(300.dp)
            ) {

                val biasX = size.center.x - 300f
                val biasY = size.center.y - 300f

                val rectF1 = RectF(0f + biasX, 0f + biasY, 600f + biasX, 600f + biasY)
                val rect1 = Rect(0f + biasX, 0f + biasY, 600f + biasX, 600f + biasY)
                val rectF2 = RectF(
                    600f - 600 * f.value + biasX,
                    -100f + biasY,
                    1400f - 600 * f.value + biasX,
                    700f + biasY
                )
                val rect2 = Rect(
                    600f - 600 * f.value + biasX,
                    -100f + biasY,
                    1400f - 600 * f.value + biasX,
                    700f + biasY
                )
                val range = { rectF2.centerX() - rectF1.centerX() }


                val rF3 = RectF(0f, 0f, 300f + 100 * f.value, 300f + 100 * f.value)

                val path = android.graphics.Path().apply {
//            moveTo(0f, 0f)
                    arcTo(
                        rectF1,
                        -getAngle(400f, 300f, range()),
                        getAngle(400f, 300f, range()) * 2,
                        false
                    )
//                        arcTo(rectF2, 180 - getAngle(300f, 400f, range(), true), 180 + getAngle(300f, 400f, range()), false)
//                        Log.d("!!!", "${180f - getAngle(400f, 300f, range())}  ${180f + getAngle(400f, 300f, range())}")

//                        arcTo(rF3, 350f, 50f)
                }

                val path2 = android.graphics.Path().apply {
                    arcTo(
                        rectF2,
                        180 - getAngle(300f, 400f, range(), true),
                        2 * getAngle(300f, 400f, range()),
                        false
                    )
                }

                val angel1 = 180 - getAngle(400f, 300f, range())
                val angel2 = getAngle(300f, 400f, range(), true)


                val path4 = Path().apply {
                    arcTo(
                        rect1,
                        180 - angel1,
                        2 * angel1,
                        false
                    )
                    arcTo(
                        rect2,
                        180 + angel2,
                        -2 * angel2,
                        false
                    )
                }


                val canvasWidth = size.width
                val canvasHeight = size.height


                drawText(
                    textMeasurer = textM,
                    text = number.toString(),
                    topLeft = Offset(
                        (canvasWidth - textSize.width) / 2f,
                        (canvasHeight - textSize.height) / 2f
                    ),
                    style = TextStyle(fontSize = fontSize.sp, color = Color.White),
                )

                clipPath(path4) {
                    drawText(
                        textMeasurer = textM2,
                        text = number.toString(),
                        topLeft = Offset(
                            (canvasWidth - textSize.width) / 2f,
                            (canvasHeight - textSize.height) / 2f
                        ),
                        style = TextStyle(fontSize = fontSize.sp)
                    )
                }
            }

            }

    }
}


fun getAngle(a: Float, b: Float, c: Float, print: Boolean = false): Float {

    val f = acos((b * b + c * c - a * a) / (2 * b * c)) * 180 / PI
//    if (print) Log.d("!!!", "a = $a  b = $b   c = $c   f = $f")
//    if (print) Log.d("!!!", ((b * b + c * c - a * a) / (2 * b * c)).toString())
//    if (c > 1 + b) return 0f
    return f.toFloat()
}
