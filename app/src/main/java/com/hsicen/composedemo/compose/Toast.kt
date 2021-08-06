package com.hsicen.composedemo.compose

import android.content.Context
import android.widget.Toast

/**
 * 作者：hsicen  8/6/21 17:42
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ComposeDemo
 */

inline fun Context.info(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
