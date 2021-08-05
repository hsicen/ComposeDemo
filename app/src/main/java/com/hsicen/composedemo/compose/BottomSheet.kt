package com.hsicen.composedemo.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * 作者：hsicen  8/5/21 11:14
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：底部弹窗
 */

@ExperimentalMaterialApi
@Composable
fun BottomSheet() {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden, tween(500))
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(sheetState = state, sheetContent = {
        //弹窗 content
        Column {
            ListItem(text = { Text(text = "选择分享到哪里吧~") })

            ShareItem(text = "Github", icon = Icons.Filled.Star)
            ShareItem(text = "微信", icon = Icons.Filled.Send)
            ShareItem(text = "微博", icon = Icons.Filled.Share)
            ShareItem(text = "抖音", icon = Icons.Filled.Star)
            ShareItem(text = "快手", icon = Icons.Filled.Settings)
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { scope.launch { state.show() } }) {
                Text(text = "点击开始分享")
            }
        }

        BackHandler(
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                    || state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch { state.hide() }
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ShareItem(text: String, icon: ImageVector) {
    val context = LocalContext.current

    ListItem(text = { Text(text = text) }, icon = {
        Surface(shape = CircleShape, color = Color(0xFF181717)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }
    }, modifier = Modifier.clickable {
        Toast.makeText(context, "点击${text}分享", Toast.LENGTH_SHORT).show()
    })
}