package com.hsicen.composedemo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast

/**
 * 作者：hsicen  8/9/21 11:00
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ConstraintLayout
 */

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
fun ConstraintHelper() {
    ConstraintLayout {
        val (btn1, text, btn2) = createRefs()

        Button(onClick = { }, modifier = Modifier.constrainAs(btn1) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "Button 1")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(btn1.bottom, margin = 16.dp)
            centerAround(btn1.end)
        })

        val endBarrier = createEndBarrier(btn1, text)
        Button(onClick = {}, Modifier.constrainAs(btn2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(endBarrier)
        }) {
            Text(text = "Button 2")
        }
    }
}

@Composable
fun ConstraintContent() {
    ConstraintLayout {
        val text = createRef()

        val startGuide = createGuidelineFromStart(0.5f)
        Text(text = "This is a very very long text.".repeat(10),
            Modifier.constrainAs(text) {
                linkTo(startGuide, parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            })
    }
}

@Composable
fun DecoupledConstraint() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(16.dp)
        } else {
            decoupledConstraints(32.dp)
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { },
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }

            Text(text = "Text", Modifier.layoutId("text"))
        }
    }
}

fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun TwoText(str1: String, str2: String) {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = str1, modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )
        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            text = str2, modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}


@Composable
fun ConstrintSample() {
    Column {
        ItemConstrain()
        Spacer(modifier = Modifier.size(16.dp))
        ConstraintHelper()
        Spacer(modifier = Modifier.size(16.dp))
        ConstraintContent()
        Spacer(modifier = Modifier.size(16.dp))
        DecoupledConstraint()
        Spacer(modifier = Modifier.size(16.dp))
        TwoText(str1 = "Hi", str2 = "There")
    }
}