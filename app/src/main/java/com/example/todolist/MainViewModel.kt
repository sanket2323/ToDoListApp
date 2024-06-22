package com.example.todolist

import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.todolist.Data.Todo
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainViewModel:ViewModel() {
    data class AppState(
        val showDialog: Boolean = false,
    )

    val database = Firebase.database
    val myRef = database.getReference("Tasks")



    private val _appState = mutableStateOf(AppState())
    val appState:MutableState<AppState> = _appState

    fun showDialog(todo: Todo) {
        _appState.value = AppState(showDialog = true)
    }

    fun hideDialog() {
        _appState.value = AppState(showDialog = false)
    }
}