package com.hsicen.composedemo.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun TodoScreen(
    items: List<TodoItem>,
    editItem: TodoItem? = null,
    onAddItem: (TodoItem) -> Unit = {},
    onRemoveItem: (TodoItem) -> Unit = {},
    onEditStart: (TodoItem) -> Unit = {},
    onEditChange: (TodoItem) -> Unit = {},
    onEditDone: () -> Unit = {}
) {
    Column {
        val enableTopSection = editItem == null
        TodoItemInputBackground(elevate = enableTopSection, modifier = Modifier.fillMaxWidth()) {
            if (enableTopSection) {
                TodoItemRntryInput(onItemComplete = onAddItem)
            } else {
                Text(
                    text = "Editing item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = items) { it ->
                if (editItem?.id == it.id) {
                    TodoItemInlineEditor(
                        item = editItem,
                        onEditChange = onEditChange,
                        onEditDone = onEditDone,
                        onRemoveItem = { onRemoveItem(it) }
                    )
                } else {
                    TodoRow(
                        todo = it,
                        onItemClicked = { onEditStart(it) },
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun TodoItemInlineEditor(
    item: TodoItem,
    onEditChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
) = TodoItemInput(
    text = item.task,
    onTextChange = { onEditChange(item.copy(task = it)) },
    submit = onEditDone,
    iconVisible = true,
    icon = item.icon,
    onIconChange = { onEditChange(item.copy(icon = it)) }
) {
    Row {
        val shrinkBtn = Modifier.widthIn(20.dp)
        TextButton(onClick = onEditDone, modifier = shrinkBtn) {
            Text(
                text = "\uD83D\uDCBE",
                textAlign = TextAlign.End,
                modifier = Modifier.width(30.dp)
            )
        }

        TextButton(onClick = onRemoveItem, modifier = shrinkBtn) {
            Text(
                text = "❌",
                textAlign = TextAlign.End,
                modifier = Modifier.width(30.dp)
            )
        }
    }
}

@Composable
fun TodoRow(
    todo: TodoItem,
    onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
    iconAlpha: Float = remember(todo.id) { randomTint() }
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(todo) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(todo.task)
        Icon(
            imageVector = todo.icon.imageVector,
            contentDescription = stringResource(id = todo.icon.contentDescription),
            tint = LocalContentColor.current.copy(alpha = iconAlpha)
        )
    }
}


@Composable
fun TodoItemRntryInput(onItemComplete: (TodoItem) -> Unit) {
    // keep state
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
    val iconVisible = text.isNotBlank()
    val submit = {
        if (text.isNotBlank()) {
            onItemComplete(TodoItem(text, icon))
            setIcon(TodoIcon.Default)
            setText("")
        }
    }

    TodoItemInput(text, setText, icon, setIcon, submit, iconVisible) {
        TodoEditButton(
            onClick = submit,
            text = "Add",
            enabled = text.isNotBlank()
        )
    }
}

@Composable
private fun TodoItemInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    submit: () -> Unit,
    iconVisible: Boolean,
    content: @Composable () -> Unit
) {
    Column {
        Row(
            Modifier.padding(top = 16.dp)
        ) {
            TodoInputText(
                text = text,
                onTextChange = onTextChange,
                onImeAction = submit,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(CenterVertically)) { content() }
        }

        if (iconVisible) {
            AnimatedIconRow(icon, onIconChange, Modifier.padding(top = 8.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


private fun randomTint(): Float {
    return Random.nextFloat().coerceIn(0.3f, 0.9f)
}
