package com.hsicen.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hsicen.composedemo.compose.PhotographerCaard

/**
 * 作者：hsicen  7/28/21 14:40
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Compose 入门课程
 */

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                PhotographerCaard()
            }
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            Surface(color = Color.Yellow) {
                content()
            }
        }
    }
}