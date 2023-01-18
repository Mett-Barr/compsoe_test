package com.example.compsoetest.ui.page.irom

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Iron9() {

    val state = rememberLazyListState()
    val list = List(300) { (0..1000).random() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = state,
    ) {
        items(list) {
            Text(text = it.toString())
        }
    }

//    state.firstVisibleItemScrollOffset
//    state.interactionSource
//    state.layoutInfo
//    state.animateScrollBy()

}