package com.hsicen.composedemo.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 作者：hsicen  8/6/21 15:54
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：State in compose
 */


@Composable
fun Greeting(name: String) {
    var select by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (select) Color.Yellow else Color.Transparent)

    Text(
        text = "Hello $name",
        modifier = Modifier
            .padding(24.dp)
            .background(color)
            .clickable { select = !select }
    )
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }

    Button(onClick = { count++ }) {
        Text(text = "I've been clicked $count times.")
    }
}

@Composable
fun Counter2(count: Int, update: (Int) -> Unit) {
    Button(
        onClick = { update.invoke(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text(text = "I've been clicked $count times.")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(names) { item ->
            Greeting(name = item)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun ScreenContent(names: List<String> = listOf("Android", "there")) {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        NameList(
            names = List(1000) { "Hello Android Compose #$it" },
            Modifier.weight(1f)
        )
        Counter2(count) {
            count = it
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun StatePreview() {
    ScreenContent()
}