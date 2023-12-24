package com.example.todolist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.ListRepository
import com.example.todolist.data.Task
import com.example.todolist.navigation.TaskEditScreen
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TaskEditScreenViewModel(
    private val lisiRepository: ListRepository,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
     var _state by mutableStateOf(TaskEditState())
    fun updateTaskTitle(title: String) {
        _state = _state.copy(
            taskDetails = _state.taskDetails.copy(
                title = title
            ),
            saveEnabled = validateInput()
        )
    }

    fun updateTaskDescription(description: String) {
        _state = _state.copy(
            taskDetails = _state.taskDetails.copy(
                description = description
            ),
            saveEnabled = validateInput()
        )
    }

    private fun validateInput(taskDetails: TaskDetails = _state.taskDetails): Boolean {
        return with(taskDetails) {
            title.isNotBlank() && description.isNotBlank()
        }
    }

    suspend fun insertTask() {
            if(validateInput(_state.taskDetails))
            lisiRepository.insertTask(_state.taskDetails.toTask())
    }



}

data class TaskEditState(
    val taskDetails : TaskDetails = TaskDetails(),
    val saveEnabled : Boolean = false
)

data class TaskDetails (
    val id : Int = 0,
    val title : String = "",
    val description : String = ""
)
fun TaskDetails.toTask()= Task(
    Id=id,
    title=title,
    description = description
)
fun Task.toTaskDetails() = TaskDetails(
    id = Id,
    title = title,
    description = description
)
fun Task.toTaskEditState(saveEnabled: Boolean =false ) = TaskEditState(
    taskDetails = this.toTaskDetails(),
    saveEnabled = saveEnabled
)
