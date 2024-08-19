package com.jetapptech.halfwarenote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.jetapptech.halfwarenote.presentation.nvgraph.AppScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.aboutUsScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.addNoteScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.analyticsScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.homeScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.lateScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.noteScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.onboardingScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.parametersScreen

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


    //action



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
        when(appScreen){
            homeScreen->{
                setTopBar(true , 5f)
                setBottomBar(true , 8f)
            }
            is noteScreen->{
                setTopBar(true , 5f)
                setBottomBar(false , 0f)
            }
            is addNoteScreen ->{
                setTopBar(true , 5f)
                setBottomBar(true , 8f)
            }
            parametersScreen ->{
                setTopBar(true , 5f)
                setBottomBar(true , 8f)
            }
            lateScreen ->{
                setTopBar(true , 5f)
                setBottomBar(true , 8f)
            }
            analyticsScreen ->{
                setTopBar(true , 5f)
                setBottomBar(true , 8f)
            }
            aboutUsScreen->{
                setTopBar(false , 0f)
                setBottomBar(false , 0f)
            }
            onboardingScreen ->{
                setTopBar(false , 0f)
                setBottomBar(false , 0f)
            }
            else->{
                setTopBar(false , 0f)
                setBottomBar(false , 0f)
            }
        }
    }

}