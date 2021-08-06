package com.hsicen.composedemo.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

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
fun BodyContent1() {
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


@Composable
fun StageGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(content = content, modifier) { measurables, constraints ->
        val rowWidths = IntArray(rows) { 0 }
        val rowHeights = IntArray(rows) { 0 }

        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = max(rowHeights[row], placeable.height)

            placeable
        }

        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))
            ?: constraints.minWidth

        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        layout(width, height) {
            val rowX = IntArray(rows) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )

                rowX[row] += placeable.width
            }
        }
    }
}


@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)


@Composable
fun StageContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        StageGrid(modifier = modifier, rows = 5) {
            for (topic in topics) {
                Chip(modifier = Modifier.padding(8.dp), text = topic)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LayoutPreview() {
    StageContent()
}
