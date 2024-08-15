package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.events

sealed class HomeScreenEvents {

    class setCategory(val categoryId : Int) : HomeScreenEvents()

    class DeleteNote (val noteId : Int ,val onSuccess : ()->Unit = {}) : HomeScreenEvents()
}