package com.hsicen.composedemo.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

/**
 * 作者：hsicen  8/6/21 11:07
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：卡片布局
 */
data class Message(val name: String, val avator: String, val content: String)

@Composable
fun MsgCard(msg: Message) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(4.dp))
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = msg.avator),
            contentDescription = "User Avator",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Green, CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.name, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.secondaryVariant)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.content)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun CardPreview() {
    MsgCard(msg = Message("hsicen", "", "hello compose"))
    MsgCard(msg = Message("hsicen", "", "hello compose"))
}
