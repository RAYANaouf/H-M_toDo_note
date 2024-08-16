package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.addNoteViewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.dao.Category_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.CheckBox_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Media_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Paragraph_Dao
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.data.local.room.entities.CheckBox_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Paragraph_Room
import com.jetapptech.halfwarenote.data.local.room.relations.NoteAndComponents
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.events.AddNoteEvents
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.screenData.AddNoteScreen_Scene
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.screenData.main
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.screenData.saving
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddNoteViewModel  constructor(
    private val noteDao      : Note_Dao,
    private val paragraphDao : Paragraph_Dao,
    private val mediaDao     : Media_Dao,
    private val checkBoxDao  : CheckBox_Dao,
    private val categoryDao  : Category_Dao
    ) : ViewModel() {



    var scene : AddNoteScreen_Scene by mutableStateOf(main)
        private set

    var categories  = mutableStateListOf<Category_Room>()
        private set

    var note : NoteAndComponents? by mutableStateOf(null)
        private set


    init {

        viewModelScope.launch {
            categoryDao.getCategories().collect{
                categories.clear()
                categories.addAll(it)
            }
        }

    }

    fun onEvent(event : AddNoteEvents, onSave : ()-> Unit){
        when(event){
            is AddNoteEvents.saveNote -> {

                scene = saving

                viewModelScope.launch {

                    val noteId = async {
                        noteDao.insert(event.note)
                    }.await()


                    val components = event.components

                    var childJob = launch {
                        components.forEach {

                            when(it){
                                is Paragraph -> {
                                    paragraphDao.insert(Paragraph_Room(txt = it.txt , note_id = noteId , index = it.index))
                                }
                                is CheckBox -> {
                                    checkBoxDao.insert(CheckBox_Room(txt = it.txt  , note_id = noteId , index = it.index , checked = it.checked))
                                }
                                is Media -> {
                                    mediaDao.insert(Media_Room(img = it.img  , note_id = noteId , index = it.index))
                                }
                            }
                        }
                    }



                    childJob.join()

                    withContext(Dispatchers.Main){
                        onSave()
                    }




                }

            }

            is AddNoteEvents.editNote -> {

                scene = saving

                viewModelScope.launch {

                    val noteId = async {
                        noteDao.insert(event.note)
                    }.await()


                    val components = event.components

                    var childJob = launch {
                        components.forEach {

                            when(it){
                                is Paragraph -> {
                                    paragraphDao.insert(Paragraph_Room(id = it.id, txt = it.txt , note_id = event.note.id.toLong() , index = it.index))
                                }
                                is CheckBox -> {
                                    checkBoxDao.insert(CheckBox_Room(id = it.id, txt = it.txt  , note_id = event.note.id.toLong() , index = it.index , checked = it.checked))
                                }
                                is Media -> {
                                    mediaDao.insert(Media_Room(id = it.id, img = it.img  , note_id = event.note.id.toLong() , index = it.index))
                                }
                            }
                        }
                    }



                    childJob.join()

                    withContext(Dispatchers.Main){
                        onSave()
                    }




                }

            }

            is AddNoteEvents.createCategory -> {

                viewModelScope.launch {
                    categoryDao.insert(Category_Room( category = event.category))
                }

            }

            else -> {

            }
        }
    }


    fun getNoteById(noteId : Int){
        viewModelScope.launch {
            note = noteDao.getNoteById(noteId = noteId)
        }
    }




}