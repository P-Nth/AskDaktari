package com.example.askdaktari.home.location

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

class ViewMap: ViewModel() {

    var state by mutableStateOf(MapState())

}