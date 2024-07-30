package com.jetapptech.halfwarenote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.jetapptech.halfwarenote.presentation.nvgraph.AppScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.homeScreen

class MainViewModel : ViewModel() {

    var show_topbar    by mutableStateOf(false)
        private set
    var topbar_shadow  by mutableStateOf(0.dp)
        private set
    var show_bottombar by mutableStateOf(false)
        private set
    var bottombar_shadow  by mutableStateOf(0.dp)
        private set

    var current_screen : AppScreen by mutableStateOf(homeScreen)


    fun setTopBar(show : Boolean, shadow : Float) {
        show_topbar = show
        topbar_shadow = shadow.dp
    }

    fun setBottomBar(show : Boolean, shadow : Float) {
        show_bottombar = show
        bottombar_shadow = shadow.dp
    }

    fun setCurrentScreen( appScreen : AppScreen) {
        current_screen = appScreen
    }

}