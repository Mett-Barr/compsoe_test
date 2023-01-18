package com.example.compsoetest.ui.page.test

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

const val URL = "content://com.android.calendar/reminders"

@Preview
@Composable
fun CalendarTest() {

    val context = LocalContext.current

    val cursor = context.contentResolver.query(Uri.parse(URL), null, null, null, null, null)

    try {
        if (cursor == null) {
            Log.d("!!", "CalnedarTest: null")
            return
        }
        val i = cursor.count
        if (i > 0) {
            Log.d("!!", "CalnedarTest: $i")
            return
        } else Log.d("!!", "CalnedarTest: $i")

    } finally {
        Log.d("!!", "CalnedarTest: != null")
    }

    Box(modifier = Modifier.fillMaxSize()) {

    }

}