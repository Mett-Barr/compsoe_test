package com.example.compsoetest.ui.page.test

import android.content.ComponentName
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun TranslationTest() {

    val context = LocalContext.current

    val cmp1 =
        ComponentName("com.google.android.apps.translate", ".copydrop.gm3.TapToTranslateActivity")
    val cmp2 = ComponentName(
        "com.google.android.apps.translate",
        "com.google.android.apps.translate.TranslateActivity"
    )

    val intent = Intent().apply {
        action = Intent.ACTION_PROCESS_TEXT
        type = "text/plain"

        putExtra(Intent.EXTRA_TEXT, "text")
        setAction(Intent.ACTION_SEND)
//        setPackage("com.google.android.apps.translate")
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

//        component = ComponentName(
//            "com.google.android.apps.translate",
//            "com.google.android.apps.translate.QuickTranslateActivity"
//        )
        component = ComponentName(
            "com.google.android.apps.translate",
            ".copydrop.gm3.TapToTranslateActivity"
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            context.startActivities(arrayOf(intent))
        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Translate")
        }
    }
}