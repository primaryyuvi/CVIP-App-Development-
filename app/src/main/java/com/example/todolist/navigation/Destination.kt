package com.example.todolist.navigation

object Destination : Nav{
    override val route = "list_screen"
}
object TaskEditScreen : Nav {
    override val route = "task_edit"
    const val taskIdArg = "taskId"
    val routeWithArgs = "$route/{$taskIdArg}"
}