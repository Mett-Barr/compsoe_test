package com.example.compsoetest.ui.page.test

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShadowTest() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1)) {
            Box(modifier = Modifier
                .background(Color.Transparent)
                .animateContentSize()) {
                Card(modifier = Modifier
//                    .background(MaterialTheme.colors.background)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(200.dp), elevation = 24.dp) {

                }
            }
        }
    }
}

@Composable
fun Test() {
    BottomAppBar {

    }
}