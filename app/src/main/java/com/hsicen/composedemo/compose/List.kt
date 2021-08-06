package com.hsicen.composedemo.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

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
