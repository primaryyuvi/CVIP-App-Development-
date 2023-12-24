package com.example.todolist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.AppViewModelProvider
import com.example.todolist.R
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEntryScreen(
    taskEditScreenViewModel: TaskEditScreenViewModel= viewModel(factory = AppViewModelProvider.factory),
    navigateBack: () -> Unit

) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
           TaskEditScreenTopBar(title = "Enter Task") {
               navigateBack()
           }
        }
    ) {
        TaskEdit(
            taskDetails =  taskEditScreenViewModel._state.taskDetails,
            onTitleChange = {
                            coroutineScope.launch {
                                taskEditScreenViewModel.updateTaskTitle(it)
                            }
            },
            onDescriptionChange = {
                coroutineScope.launch {
                    taskEditScreenViewModel.updateTaskDescription(it)
                }
            },
            onSaveClick = {
                coroutineScope.launch {
                    taskEditScreenViewModel.insertTask()
                    navigateBack()
                }
            },
            onSaveEnabled = taskEditScreenViewModel._state.saveEnabled ,
            modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
        )
    }
}
@Composable
fun TaskEdit(
    taskDetails: TaskDetails,
    onTitleChange : (String) -> Unit,
    onDescriptionChange : (String) -> Unit,
    onSaveClick : () -> Unit,
    onSaveEnabled : Boolean,
    modifier : Modifier
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TitleEdit(
            taskDetails = taskDetails,
            onTitleChange = onTitleChange
        )
        DescriptionEdit(
            taskDetails = taskDetails ,
            onDescriptionChange = onDescriptionChange
        )
        SaveButton(
            onClick = onSaveClick,
            isEnabled = onSaveEnabled
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleEdit(
    taskDetails: TaskDetails,
    onTitleChange : (String) -> Unit
) {
    OutlinedTextField(
        value = taskDetails.title,
        onValueChange = onTitleChange,
        label = {
            Text(stringResource(id = R.string.title_label))
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Maximum lines you can enter is 1")
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionEdit(
    taskDetails: TaskDetails,
    onDescriptionChange : (String) -> Unit
) {
    OutlinedTextField(
        value = taskDetails.description,
        onValueChange = onDescriptionChange,
        label = {
            Text(stringResource(id = R.string.description_label))
        },
        singleLine = false,
        textStyle = MaterialTheme.typography.bodySmall,
        maxLines = 5,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Maximum lines you can enter is 5")
        }
    )
}
@Composable
fun SaveButton(
    onClick: () -> Unit,
    isEnabled : Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = "Save",
            style = MaterialTheme.typography.titleMedium
            )
    }
}
@Preview(showBackground = true)
@Composable
fun TitleEditPreview() {
    TaskEdit(
        taskDetails = TaskDetails(
            title = "Title",
            description = "Description"
        ),
        onTitleChange ={} ,
        onDescriptionChange ={} ,
        onSaveClick = { /*TODO*/ },
        onSaveEnabled = false,
        modifier = Modifier
    )
}