package com.example.compsoetest.ui.page.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardTest() {

//    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface), elevation = 24.dp) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface)) {
            repeat(5) { CardItem() }
        }
//    }

}

@Composable
fun CardItem() {
    Card(modifier = Modifier
        .padding(50.dp)
        .size(150.dp)
        .fillMaxSize(),
        elevation = 24.dp
    ) {

    }

}