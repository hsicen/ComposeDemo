package com.hsicen.composedemo.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.hsicen.composedemo.R

/**
 * 作者：hsicen  8/5/21 13:48
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ComposeDemo
 */

@Composable
fun NewStory() {
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            UserPrivate()
        }
    }
}

@Composable
fun Poetic() {
    val typography = MaterialTheme.typography
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.header),
        contentDescription = "",
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                Toast
                    .makeText(context, "Dont push me so far !", Toast.LENGTH_SHORT)
                    .show()
            })
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
}

@Composable
fun Padding() {
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
}

@Composable
fun BaseLine() {
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
}

@Composable
fun Offset() {
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

@Composable
fun FixedSize() {
    Box {
        Box(
            Modifier
                .size(90.dp, 150.dp)
                .background(Color.Green)
        ) {
            Box(
                Modifier
                    .requiredSize(100.dp, 100.dp)
                    .background(Color.Red)
            )
        }

    }
}

@Composable
fun Weight() {
    Row(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(2f)
                .height(50.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(70.dp)
                .background(Color.Yellow)
        )
    }
}

@Composable
fun Constrain() {
    BoxWithConstraints {
        Text("My height[$minHeight,$maxHeight] and width[$minWidth,$maxWidth]")
    }
}

@Composable
fun ItemConstrain() {
    ConstraintLayout {
        val (button, text) = createRefs()

        Button(onClick = {}, modifier = Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "Button")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
        })
    }
}


@Composable
fun UserPrivate() {
    val dialogState = remember { mutableStateOf(false) }

    if (dialogState.value) {
        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = {
                Text(text = "用户协议", fontSize = 24.sp, style = MaterialTheme.typography.h1)
            }, text = {
                Text(text = "这个是用户协议内容，你看到我的这段话的时候，说明你的弹窗已经出现，现在可以进行下一个联系了！")
            }, confirmButton = {
                TextButton(onClick = {
                    dialogState.value = false
                }) { Text(text = "确认") }
            }, dismissButton = {
                TextButton(onClick = {
                    dialogState.value = false
                }) { Text(text = "取消") }
            }, properties = DialogProperties(dismissOnClickOutside = false)
        )
    }

    val text = buildAnnotatedString {
        append("勾选即代表同意")
        pushStringAnnotation("privaceTag", "privace")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("用户协议")
        }
        pop()
    }

    ClickableText(text = text, onClick = {
        text.getStringAnnotations("privaceTag", it, it).firstOrNull()?.let {
            dialogState.value = true
        }
    })
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    NewStory()
}