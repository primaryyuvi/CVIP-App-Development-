package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.ListRepository
import com.example.todolist.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoListViewModel( private val listRepository: ListRepository ) : ViewModel() {
    companion object{
        private const val TIME = 5_000L
    }
    val toDoListState: StateFlow<ToDoListState> =
        listRepository.getAllTasks().map { ToDoListState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME),
                initialValue = ToDoListState()
            )

    fun deleteItem(task: Task) {
        viewModelScope.launch {
            listRepository.deleteTask(task)
        }
    }
}

data class ToDoListState(
    val tasks : List<Task> = listOf(),
    val expanded : Boolean = false
)
