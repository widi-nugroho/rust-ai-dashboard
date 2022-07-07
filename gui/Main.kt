// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import gui.componentConfigScreen
import gui.loadSaveJsonScreen
import model.ConfigData

@Composable
fun appScreen(){
    var screenState:MutableState<Int> = rememberSaveable(){
        mutableStateOf(0)
    }
    if (screenState.value==0){
        loadSaveJsonScreen(screenState)
    }else if (screenState.value==1){
        componentConfigScreen(screenState)
    }
}


fun main() = application {
    var configData = ConfigData
    Window(onCloseRequest = ::exitApplication) {
        Surface(color = Color.DarkGray){
            appScreen()
        }

    }
}
