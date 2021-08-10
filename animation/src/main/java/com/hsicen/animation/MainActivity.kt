package com.hsicen.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hsicen.animation.ui.AnimationCodelabTheme
import com.hsicen.animation.ui.home.Home

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationCodelabTheme {
                Home()
            }
        }
    }
}
