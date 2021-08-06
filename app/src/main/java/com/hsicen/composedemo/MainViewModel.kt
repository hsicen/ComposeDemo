package com.hsicen.composedemo

import androidx.lifecycle.ViewModel
import com.hsicen.composedemo.compose.Message

/**
 * 作者：hsicen  8/6/21 11:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ComposeDemo
 */
class MainViewModel : ViewModel() {
    private val avator = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpayposter.com%2Fposter_preview%2F1920x1200-hd-48710014.jpg"


    fun provideMsg(): List<Message> {
        val msgs = ArrayList<Message>()

        repeat(50) {
            msgs.add(Message("hsicen", avator, "hello compose demo"))
        }

        return msgs
    }
}