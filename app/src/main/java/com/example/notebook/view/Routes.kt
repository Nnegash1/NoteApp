package com.example.notebook.view

sealed class Routes(val routes: String) {
    object HomeScreen : Routes("home_screen")
    object AddScreen : Routes("add_todo_screen")
    object EditScreen : Routes("edit_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(routes)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
