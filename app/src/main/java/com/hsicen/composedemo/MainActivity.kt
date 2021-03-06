package com.hsicen.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.hsicen.composedemo.todo.TodoScreen
import com.hsicen.composedemo.todo.TodoViewModel

/**
 * 作者：hsicen  7/28/21 14:40
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Compose 入门课程
 */

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<MainViewModel>()
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                TodoActivityScreen(todoViewModel)
            }
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            Surface {
                content()
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    TodoScreen(
        items = todoViewModel.todoItems,
        editItem = todoViewModel.currentEditItem,
        onAddItem = todoViewModel::addItem,
        onRemoveItem = todoViewModel::removeItem,
        onEditStart = todoViewModel::onEditItemSelected,
        onEditChange = todoViewModel::onEditItemChange,
        onEditDone = todoViewModel::onEditDone
    )
}