package com.example.todolist.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Composables.topAppBar
import com.example.todolist.Data.Todo
import com.example.todolist.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun HomePage(viewModel: MainViewModel) {
    val database = Firebase.database
    val myRef = database.getReference("Tasks")
    var todoListState by remember {
        mutableStateOf(ArrayList<Todo>())
    }
    Scaffold(
        topBar = {
            topAppBar(title = "TODo List")
        }
    ) {
        LaunchedEffect(Unit) {
            myRef.get().addOnSuccessListener {docs->
                val todoList = arrayListOf<Todo>()
                val documents =docs.children
                for (document in documents){
                    val todo = Todo(
                        title = document.child("title").value.toString(),
                        description = document.child("description").value.toString()
                    )
                    todoList.add(todo)
                }
                todoListState = todoList
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(todoListState){
                TodoItem(todo = it, viewModel = viewModel)
            }
        }

    }
}


@Composable
fun TodoItem(todo: Todo, viewModel: MainViewModel) {
    val database = Firebase.database
    val myRef = database.getReference("Tasks")
    var check by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp).weight(3f)
            ) {
                myRef.get().addOnSuccessListener {
                    if (it.exists()) {
                        it.children.forEach {
                            todo.title = it.child("title").value.toString()
                            todo.description = it.child("description").value.toString()
                        }
                        check = true
                    }

                }
                Text(text = todo.title, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = todo.description, fontFamily = FontFamily.Monospace)

            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Icon"
                    )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
            }
        }

    }
}