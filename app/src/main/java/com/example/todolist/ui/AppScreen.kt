package com.example.todolist.ui

import android.util.EventLogTags.Description
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Layout() {
    var taskInput by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }

    Scaffold (
        topBar = { TodoTopAppBar() },
        bottomBar = {
            Row {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(64.dp)
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    TextField(
                        value = taskInput,
                        onValueChange = { taskInput = it },
                        label = { Text(text = "Add a task") },
                        modifier = Modifier
                            .height(64.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1,
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    tasks.add(Task(taskInput))
                                    taskInput = ""
                                },
                                modifier = Modifier
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Adding button"
                                )
                            }
                        }
                    )
                }
            }
        }
    ){

        LazyColumn(contentPadding = it) {
            itemsIndexed(tasks) { index,task ->
                TaskView(task = task,
                    modifier = Modifier,
                    deleteTask = {
                        tasks.removeAt(index)
                    }
                )
            }
        }
    }
}
@Composable
fun TaskView(task: Task,modifier: Modifier, deleteTask : () ->Unit) {
    var checkedInput by remember { mutableStateOf(false) }

    Card (
        Modifier
            .padding(8.dp)
            .height(50.dp)){
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(modifier = modifier) {

            }
            IconToggleButton(
                checked = checkedInput,
                onCheckedChange = { checkedInput = it },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = if (!checkedInput) Icons.Outlined.CheckCircle else Icons.Filled.CheckCircle,
                    contentDescription = "Check Icon",
                    modifier = Modifier.height(16.dp)
                )
            }
            IconButton(onClick = deleteTask) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }
}
@Composable
fun Description(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = modifier,
    )
}
@Composable
fun title(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 24.sp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.to_do_list),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors =   TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}
