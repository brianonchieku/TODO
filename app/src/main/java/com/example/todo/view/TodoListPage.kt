package com.example.todo.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.model.Todo
import com.example.todo.viewmodel.TodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(viewModel: TodoViewModel){

    var inputText by remember {
        mutableStateOf("")
    }

    val todoList by viewModel.todoList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(top = 40.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OutlinedTextField(value = inputText, onValueChange = {inputText=it},
                modifier = Modifier.weight(1f))

            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Text(text = "Add")

            }

        }

        todoList?.let{
            LazyColumn(
                content = {
                    itemsIndexed(todoList!!){ index: Int, item: Todo ->  
                        TodoItem(item = item)
                    }
                }
            )
                
            
        }

    }
}

@Composable
fun TodoItem(item: Todo){
    Text(text = item.toString())
}