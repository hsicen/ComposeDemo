package com.hsicen.composedemo.compose

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

/**
 * 作者：hsicen  8/4/21 17:05
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ALertDialog组件
 */

@Composable
fun SimpleAlert() {
    val openState = remember { mutableStateOf(false) }

    if (openState.value) {
        AlertDialog(onDismissRequest = {
            openState.value = false
        }, confirmButton = {
            TextButton(onClick = {
                openState.value = false
            }) { Text(text = "👌好的") }
        }, dismissButton = {
            TextButton(onClick = {
                openState.value = false
            }) { Text(text = "🚫不行") }
        }, title = {
            Text(text = "开启位置服务", textAlign = TextAlign.Center)
        }, text = {
            Text(text = "我们需要获取你的位置信息，用于为你提供更加个性化的推荐。")
        })
    }

    TextButton(onClick = {
        openState.value = true
    }) {
        Text(text = "请求位置权限")
    }
}

@Preview(showSystemUi = true)
@Composable
fun AlertPreview() {
    MaterialTheme {
        SimpleAlert()
    }
}