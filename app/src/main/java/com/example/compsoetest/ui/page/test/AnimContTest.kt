package com.example.compsoetest.ui.page.test

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimContTest() {

    var boo by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { boo = !boo }, contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier
                .background(Color.Blue)
                .size(40.dp))
            AnimatedContent(targetState = boo) {
                Text(
                    text = if (boo) "123" else "qwe\nasd\nzxc",
                    modifier = Modifier,
                    style = Typography().h5
                )
            }
        }
    }
}