package com.example.todolist.Composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(
    title: String, modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                fontWeight = FontWeight.Bold,
                text = title,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        },
        modifier = Modifier.padding(top = 0.dp),

    )
}
