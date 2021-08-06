package com.hsicen.composedemo.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

/**
 * 作者：hsicen  8/6/21 11:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Compose列表
 */

@Composable
fun Conversation(msgs: List<Message>) {
    LazyColumn {
        items(msgs) { msg ->
            MsgCard(msg = msg)
        }
    }
}

@Composable
fun SimpleList() {
    Column {
        repeat(100) {
            Text(
                "Item list from column #$it",
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun SimpleList2() {
    val state = rememberScrollState()

    Column(Modifier.verticalScroll(state)) {
        repeat(100) {
            Text(
                "Item list from column scroll #$it",
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun SimpleList3() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to bottom.")
            }

            Button(onClick = {
                scope.launch {
                    state.animateScrollToItem(99)
                }
            }) {
                Text("Scroll to top.")
            }
        }

        LazyColumn(state = state) {
            items(100) {
                Text(
                    "Item list from lazy column #$it",
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = Images.net),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Image item list #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {
        Row(Modifier.padding(8.dp)) {
            Button(onClick = {
                scope.launch {
                    state.animateScrollToItem(19)
                }
            }) {
                Text("Scroll to bottom.")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                scope.launch {
                    state.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to top.")
            }
        }

        LazyColumn(state = state) {
            items(20) {
                ImageListItem(index = it)
            }
        }
    }
}
