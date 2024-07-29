package com.jetapptech.hw_todo_note.presentation.screens.addNoteScreen.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapptech.hw_todo_note.data.local.dataClasses.CheckBox
import com.jetapptech.hw_todo_note.data.local.dataClasses.Media
import com.jetapptech.hw_todo_note.data.local.dataClasses.Note
import com.jetapptech.hw_todo_note.data.local.dataClasses.Paragraph
import com.jetapptech.hw_todo_note.data.local.room.dao.CheckBox_Dao
import com.jetapptech.hw_todo_note.data.local.room.dao.Media_Dao
import com.jetapptech.hw_todo_note.data.local.room.dao.Note_Dao
import com.jetapptech.hw_todo_note.data.local.room.dao.Paragraph_Dao
import com.jetapptech.hw_todo_note.data.local.room.database.Database
import com.jetapptech.hw_todo_note.data.local.room.entities.CheckBox_Room
import com.jetapptech.hw_todo_note.data.local.room.entities.Media_Room
import com.jetapptech.hw_todo_note.data.local.room.entities.Note_Room
import com.jetapptech.hw_todo_note.data.local.room.entities.Paragraph_Room
import com.jetapptech.hw_todo_note.data.local.room.relations.NoteAndComponents
import com.jetapptech.hw_todo_note.presentation.screens.addNoteScreen.events.AddNoteEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteDao      : Note_Dao,
    private val paragraphDao : Paragraph_Dao,
    private val mediaDao     : Media_Dao,
    private val checkBoxDao  : CheckBox_Dao
    ) : ViewModel() {



    var scene by mutableStateOf("main")



    fun onEvent(event : AddNoteEvents , onSave : ()-> Unit){
        when(event){
            is AddNoteEvents.saveNoten -> {

                scene = "saving"


                GlobalScope.launch {

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
//                        scene = "main"
                        onSave()
//                        Toast.makeText(context , "$notesAndComponents" , Toast.LENGTH_LONG).show()
                    }




                }


            }

            else -> {

            }
        }
    }

}