package gui

import androidx.compose.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.ConfigData

@Composable
fun loadSaveJsonScreen(screenState:MutableState<Int>){
    var jsonPath by remember {
        mutableStateOf(TextFieldValue())
    }
    var count = 0
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Load Json Config",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Config file path",
            fontSize = 15.sp,
            color = Color.White
        )
        OutlinedTextField(
            value = jsonPath,
            onValueChange ={jsonPath = it},
            textStyle = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Button(onClick = {
                ConfigData.loadConfigFile(jsonPath.text)
                screenState.value = 1
            }){
                Text("Load Config File")
            }

        }


    }
}
