package com.hsicen.composedemo.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * 作者：hsicen  8/9/21 15:12
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ComposeDemo
 */
class TodoViewModel : ViewModel() {

    private var currentEditPosition by mutableStateOf(-1)
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    var todoItems = mutableStateListOf<TodoItem>()
        private set


    fun addItem(item: TodoItem) {


        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
        onEditDone()
    }

    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }
        todoItems[currentEditPosition] = item
    }

}