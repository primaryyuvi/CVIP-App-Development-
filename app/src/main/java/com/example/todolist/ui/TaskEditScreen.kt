@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun TaskEditScreen(
    navigateBack: () -> Unit,
    taskEditViewModel: TaskEditViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TaskEditScreenTopBar(title = "Edit Task") {
                navigateBack()
            }
        }
    ) {
        TaskEdit(
            taskDetails = taskEditViewModel.taskEditUiState.taskDetails,
            onTitleChange = {
                    coroutineScope.launch {
                        taskEditViewModel.updateTaskTitle(it)
                    }
            },
            onDescriptionChange = {
                     coroutineScope.launch {
                         taskEditViewModel.updateTaskDescription(it)
                     }
            },
            onSaveClick = {
                    coroutineScope.launch {
                        taskEditViewModel.updateTask()
                        navigateBack()
                    }
            },
            onSaveEnabled = taskEditViewModel.taskEditUiState.saveEnabled,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}