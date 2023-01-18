package com.example.compsoetest.ui.page.test

import android.app.NotificationManager
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.compsoetest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun NotificationTest() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var builder = NotificationCompat.Builder(context, "test")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("title")
        .setContentText("content")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {     with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, builder.build())
        }
        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Notification Test")
        }
    }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while (true) {
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(0, builder.build())
                }
                delay(2000)

                (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(0)
                delay(2000)
            }
        }
    }

//    fun noti() {

//    }
}