package com.example.todolist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.ListRepository
import com.example.todolist.navigation.TaskEditScreen
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TaskEditViewModel (
    private val lisiRepository: ListRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    var taskEditUiState by mutableStateOf(TaskEditState())
        private set

    private val id : Int = checkNotNull(savedStateHandle[TaskEditScreen.taskIdArg])
    init {
        viewModelScope.launch {
            taskEditUiState = lisiRepository.getItemStream(id)
                .filterNotNull()
                .first()
                .toTaskEditState(true)
        }
    }

    fun updateTaskTitle(title: String) {
        taskEditUiState = taskEditUiState.copy(
            taskDetails = taskEditUiState.taskDetails.copy(
                title = title
            ),
            saveEnabled = validateInput()
        )
    }

    fun updateTaskDescription(description: String) {
        taskEditUiState = taskEditUiState.copy(
            taskDetails = taskEditUiState.taskDetails.copy(
                description = description
            ),
            saveEnabled = validateInput()
        )
    }

    private fun validateInput(taskDetails: TaskDetails = taskEditUiState.taskDetails): Boolean {
        return with(taskDetails) {
            title.isNotBlank() && description.isNotBlank()
        }
    }

    fun updateTask() {
        viewModelScope.launch {
            if(validateInput(taskEditUiState.taskDetails))
                lisiRepository.insertTask(taskEditUiState.taskDetails.toTask())
        }
    }
}