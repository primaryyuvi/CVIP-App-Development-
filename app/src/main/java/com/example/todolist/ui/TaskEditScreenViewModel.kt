package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.ToDoListApplication
import com.example.todolist.data.ListRepository
import com.example.todolist.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskEditScreenViewModel(private val lisiRepository: ListRepository ) : ViewModel() {
    private val _state = MutableStateFlow(TaskEditState())
    val state = _state.asStateFlow()

    fun updateTaskTitle(title: String) {
        _state.value = _state.value.copy(
            taskDetails = _state.value.taskDetails.copy(
                title = title
            ),
            saveEnabled = validateInput()
        )
    }

    fun updateTaskDescription(description: String) {
        _state.value = _state.value.copy(
            taskDetails = _state.value.taskDetails.copy(
                description = description
            ),
            saveEnabled = validateInput()
        )
    }

    private fun validateInput(taskDetails: TaskDetails = state.value.taskDetails): Boolean {
        return with(taskDetails) {
            title.isNotBlank() && description.isNotBlank()
        }
    }

    fun insertTask() {
        viewModelScope.launch {
            lisiRepository.insertTask(_state.value.taskDetails.toTask())
        }
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
fun Task.toTaskEditState() = TaskEditState(
    taskDetails = this.toTaskDetails()
)
