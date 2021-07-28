package com.hsicen.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsicen.composedemo.ui.theme.ComposeDemoTheme

/**
 * 作者：hsicen  7/28/21 14:40
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Compose 入门课程
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                NewStory()
            }
        }
    }
}

@Composable
fun NewStory() {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = "",
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clickable(onClick = {})
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "锄禾",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = "锄禾日当午", style = typography.body2)
            Text(text = "汗滴禾下土", style = typography.body2)
            Text(text = "谁知盘中餐", style = typography.body2)
            Text(text = "粒粒皆辛苦", style = typography.body2)

            Text(
                "Hello World",
                modifier = Modifier
                    .background(Color.Green)
                    .padding(20.dp)
            )

            Box(
                Modifier
                    .background(Color.Blue)
                    .size(50.dp)
                    .padding(10.dp)
            ) {
                Box(
                    Modifier
                        .background(Color.Gray)
                        .fillMaxSize()
                )
            }

            Box {
                Spacer(
                    Modifier
                        .matchParentSize()
                        .background(Color.Green)
                )
                Text(
                    text = "Hello Compose",
                    Modifier.paddingFromBaseline(top = 32.dp)
                )
            }

            //offset
            Box(
                Modifier
                    .background(Color.Yellow)
                    .size(150.dp, 70.dp)
            ) {
                Text(
                    text = "Layout offset modifier sample",
                    Modifier.offset(16.dp, 12.dp)
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewStory()
}