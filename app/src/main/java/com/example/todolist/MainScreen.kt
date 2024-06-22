package com.example.todolist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todolist.Composables.topAppBar
import com.example.todolist.Data.NavItem
import com.example.todolist.pages.AccountPage
import com.example.todolist.pages.HomePage


@Composable
fun MainScreen(modifier: Modifier = Modifier,mainViewModel: MainViewModel){

    var navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Add",Icons.Default.Add)
    )
    var selected by remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar =  {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selected == index, onClick = { selected = index },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                        label = { Text(navItem.label) },
                        )
                }
            }
        }
    ) { innerPadding ->
           MainContent(modifier = Modifier.padding(innerPadding),selected,mainViewModel)
    }
}

@Composable
fun MainContent(modifier: Modifier= Modifier,selectedIndex:Int,mainViewModel: MainViewModel){
    when(selectedIndex){
        0 -> HomePage(mainViewModel)
        1 -> AccountPage(mainViewModel)
    }
}

