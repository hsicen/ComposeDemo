package com.hsicen.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import com.hsicen.composedemo.compose.BodyContent

/**
 * 作者：hsicen  7/28/21 14:40
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Compose 入门课程
 */

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BodyContent()
            }
        }
    }
}