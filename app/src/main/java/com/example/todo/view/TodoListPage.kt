package com.example.todo.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import com.example.todo.model.Todo
import com.example.todo.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

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
   Row(
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp)
           .clip(RoundedCornerShape(16.dp))
           .background(MaterialTheme.colorScheme.primary)
           .padding(16.dp)
   ) {
       Column {
           Text(text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
               fontSize = 12.sp,
               color = Color.LightGray
           )
           Text(text = item.title,
               fontSize = 20.sp,
               color = Color.White)
       }
       IconButton(onClick = {  }) {
           Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "delete" )

       }
   }
}