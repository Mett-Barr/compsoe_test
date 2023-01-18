package com.example.compsoetest.ui.page.test

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun BrushTest() {

    var state by remember {
        mutableStateOf(true)
    }

    val transition = updateTransition(targetState = state)

    val color by animateColorAsState(targetValue = if (state) Color.Black else Color.White, animationSpec = tween(1500))

    val brush by remember {
        derivedStateOf {
            Brush.horizontalGradient(listOf(Color.Black, color))
        }
    }

    Spacer(modifier = Modifier
        .clickable { state = !state }
        .fillMaxSize()
        .background(brush))
    Crossfade(targetState = state) {
        if (it) {
            Spacer(modifier = Modifier
                .background(Color.Green)
                .size(300.dp))
        }
    }
}

@Composable
fun BrushCanvas(pointTop: Float, pointBottom: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            brush = Brush.verticalGradient(
                0f to Color.Transparent,
                pointTop to Color.Transparent,
                pointBottom to Color.Black,
                1f to Color.Black
                )
        )
    }
}