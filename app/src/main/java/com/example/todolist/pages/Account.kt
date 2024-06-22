package com.example.todolist.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.Composables.topAppBar
import com.example.todolist.Data.Todo
import com.example.todolist.MainViewModel
import com.google.android.gms.common.data.DataBufferSafeParcelable.addValue
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun AccountPage(viewModel: MainViewModel){

    var todoName by remember {
        mutableStateOf("")
    }
    var todoDescription by remember {
        mutableStateOf("")
    }

    val database = Firebase.database
    val myRef = database.getReference("Tasks")
    val context = LocalContext.current

    Scaffold(
        topBar = {
            topAppBar(title = "Add ToDo")
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            OutlinedTextField(
                value = todoName, onValueChange = {
                    todoName = it
                },
                label = { Text(text = "Todo Name") }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp, 0.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = todoDescription, onValueChange = {
                    todoDescription = it
                },
                label = { Text(text = "Todo Description") }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                            if (todoName.isNotEmpty() && todoDescription.isNotEmpty()){
                                val todoInfo = Todo(todoName,todoDescription)
                                myRef.child(todoName).setValue(todoInfo ).addOnSuccessListener {
                                    Toast.makeText(context,"Data added successfully",Toast.LENGTH_SHORT).show()
                                    todoName = ""
                                    todoDescription=""
                                }.addOnFailureListener {
                                    Toast.makeText(context,"Failed to add data",Toast.LENGTH_SHORT).show()
                                    println(it.message.toString())
                                }
                            }else{
                                Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                            }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4),
                    )
                ) {
                    Text(text = "Add")
                }
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4),
                    )
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}