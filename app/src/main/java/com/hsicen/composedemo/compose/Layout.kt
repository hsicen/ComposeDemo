package com.hsicen.composedemo.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 作者：hsicen  8/5/21 15:05
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：自定义布局
 */

@Composable
fun KColumn(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier = modifier) { measureables, constraints ->
        val placeables = measureables.map { measureable ->
            measureable.measure(constraints)
        }

        var yPos = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPos)
                yPos += placeable.height
            }
        }
    }
}

@Composable
fun BodyContent() {
    /* KColumn(modifier = Modifier.padding(8.dp)) {
         repeat(10) {
             Text(text = "My Own Colum")
         }
     }*/

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .clipToBounds()
    ) {
        item {
            repeat(30) {
                Text(text = "My Own Colum")
                Text(text = "My Own Colum")
                Text(text = "My Own Colum")
                Text(text = "My Own Colum")
                Text(text = "My Own Colum")
            }
        }
    }
}


fun Modifier.firstBaseLineToTop(distanceDp: Dp) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseLine = placeable[FirstBaseline]
    val placeableY = distanceDp.roundToPx() - firstBaseLine
    val height = placeable.height + placeableY

    layout(placeable.width, height) {
        placeable.placeRelative(0, placeableY)
    }
}


@Composable
fun CustomLayout(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Layout(content, modifier, { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach {
                //具体的layout 逻辑
            }
        }
    })
}


@Preview(showSystemUi = true)
@Composable
fun LayoutPreview() {
    BodyContent()
}
