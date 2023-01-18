package com.example.compsoetest.ui.page.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldTest() {

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue("123"))
    }

    val state = rememberLazyListState()

    val focusManager = LocalFocusManager.current

//    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current


    LazyColumn(modifier = Modifier
        .background(Color.Gray)
//        .clickable {
////            focusManager.clearFocus()
//            keyboardController?.hide()
//        }
        .fillMaxSize(),
        state = state) {
        item {
            Spacer(modifier = Modifier.height(3000.dp))
        }
        item {
            TextField(value = textFieldValue, onValueChange = { textFieldValue = it })
        }
        item {
            Spacer(modifier = Modifier
                .clickable {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
                .height(3000.dp)
                .fillMaxWidth())
        }
    }

    LaunchedEffect(Unit) {
        state.scrollToItem(1)
    }
}