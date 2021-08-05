package com.hsicen.composedemo.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 作者：hsicen  8/4/21 16:56
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：BottomNavigation组件使用
 */

@ExperimentalAnimationApi
@Composable
fun SimpleNavigation() {
    val selectItem by remember { mutableStateOf(0) }
    val items = listOf("主页", "我喜欢的", "设置")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = items[selectItem],
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }, bottomBar = {
            AnimationNavigation()
            /*BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectItem.value == index,
                        onClick = { selectItem.value = index },
                        icon = {
                            val iconImg = when (index) {
                                0 -> Icons.Filled.Home
                                1 -> Icons.Filled.Favorite
                                else -> Icons.Filled.Settings
                            }

                            Icon(iconImg, null)
                        },
                        label = { Text(item) }
                    )
                }
            }*/
        }, modifier = Modifier.fillMaxSize()
    ) {

    }
}

@ExperimentalAnimationApi
@Composable
fun AnimationNavigation() {
    val selectItem = remember { mutableStateOf(0) }

    BottomNavigation(backgroundColor = Color.White) {
        repeat(3) { index ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        onClick = {
                            selectItem.value = index
                        },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationIcon(index, selectItem.value)
                Spacer(modifier = Modifier.padding(top = 2.dp))
                AnimatedVisibility(visible = index == selectItem.value) {
                    Surface(shape = CircleShape, modifier = Modifier.size(5.dp), color = Color(0xFF252527)) {}
                }
            }
        }
    }
}

@Composable
fun NavigationIcon(index: Int, selectItem: Int) {
    val alpha = if (selectItem != index) 0.5f else 1f

    CompositionLocalProvider(LocalContentAlpha provides alpha) {
        when (index) {
            0 -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            1 -> Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            else -> Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
        }
    }
}


@ExperimentalAnimationApi
@Preview(showSystemUi = true)
@Composable
fun NavigationPreview() {
    SimpleNavigation()
}