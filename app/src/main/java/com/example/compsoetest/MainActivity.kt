package com.example.compsoetest

import android.app.Activity
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.compsoetest.ui.page.canvas.C01
import com.example.compsoetest.ui.theme.CompsoeTestTheme
import com.example.compsoetest.ui.theme.background
import java.util.*


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompsoeTestTheme {

//                var text by remember {
//                    mutableStateOf("")
//                }
//
//                val c = Calendar.getInstance()
//                text = c.time.toString()
////                text = c.timeZone.toString() + "  " + c.timeZone
////                text = TimeZone.getDefault().toString()
//
//                Column {
//                    Text(text)
//                    var text2 = ""
//                    val c2= Calendar.getInstance()
//                    c2.timeZone = TimeZone.getTimeZone("GMT+05:30")
////                    c2.timeZone = Calendar.getInstance().timeZone
//                    text2 = c2.time.toString()
//
//
////                    val time = (this@MainActivity.getSystemService(Activity.LOCATION_SERVICE) as LocationManager)
////                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!.time
//
//                    Text(text2)
//                }

//                Surface(
//                    Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {

                    val f = rememberInfiniteTransition().animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            tween(1000), RepeatMode.Reverse
                        )
                    )

                    C01()
//                }


            }


        }
    }
}