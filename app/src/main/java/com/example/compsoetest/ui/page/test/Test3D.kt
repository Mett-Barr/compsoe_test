package com.example.compsoetest.ui.page.test

import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Test3D() {
    Box(modifier = Modifier.fillMaxSize()) {
        val  sensorManager = LocalContext.current.getSystemService(SENSOR_SERVICE) as SensorManager
        val context = LocalContext.current

        Spacer(modifier = Modifier
            .align(Alignment.Center)
            .background(Color.Gray)
            .size(300.dp))
        Spacer(modifier = Modifier
            .align(Alignment.Center)
            .background(Color.DarkGray)
            .size(150.dp))

//        val state by remember {
//            derivedStateOf {
//                sensorManager.
//            }
//        }

//        DisposableEffect(Unit) {
//            val dataManager = SensorDataManager(context)
//        }
    }
}