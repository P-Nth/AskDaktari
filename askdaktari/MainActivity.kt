package com.example.askdaktari

import android.os.Bundle
import com.example.askdaktari.login.Login
import androidx.compose.runtime.Composable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskDaktari()
        }
    }
}

@Composable
fun AskDaktari() {
    val login = viewModel(modelClass = Login::class.java)
    Navigation(login = login)
}