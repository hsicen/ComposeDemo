package com.hsicen.composedemo.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * 作者：hsicen  8/6/21 17:31
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Compose Material conponents
 */

@Composable
fun LayoutCodelab() {
    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {
                        ctx.info("You clicked me!")
                    }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }

    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier.padding(48.dp)) {
        Text(text = "Hi there!")
        Text(text = "Thanks for goning through the Layouts codelab")
    }
}