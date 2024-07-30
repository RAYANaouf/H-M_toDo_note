package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.homeViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import com.jetapptech.halfwarenote.data.local.room.relations.NoteAndComponents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(
    val noteDao: Note_Dao
) : ViewModel() {


    var notes = mutableStateListOf<Note>()
        private set


    init {
        getNotes()
    }

    fun getNotes(){

        var noteRelation : Flow<List<NoteAndComponents>> = noteDao.getNoteAndComponents()

        viewModelScope.launch {

            var type = 0 ;

            noteRelation.collect{

                var noteList : List<Note>


                noteList = it.map{

                    var noteComponent = ArrayList<NoteComponent>()

                    it.paragraphs.forEach {
                        noteComponent
                            .add(
                                Paragraph(
                                    id = it.id,
                                    txt = it.txt,
                                    index = it.index
                                )
                            )
                    }

                    it.checkBoxs.forEach {
                        noteComponent
                            .add(
                                CheckBox(
                                    id = it.id,
                                    txt = it.txt,
                                    checked = it.checked,
                                    index = it.index
                                )
                            )
                    }

                    it.media.forEach {
                        type = 1
                        noteComponent
                            .add(
                                Media(
                                    id = it.id,
                                    img = it.img,
                                    index = it.index
                                )
                            )
                    }



                    val new_note = Note(
                        id         = it.note.id,
                        title      = it.note.title,
                        components = noteComponent,
                        type       = type,
                        createdAt  = Date(it.note.createdAt),
                        activateAt = Date(it.note.activateAt),
                        color      = Color(it.note.color),
                        password   = it.note.password,
                        hint       = it.note.hint
                    )

                    new_note

                }

                notes.clear()
                notes.addAll(noteList)


            }

        }

    }

}