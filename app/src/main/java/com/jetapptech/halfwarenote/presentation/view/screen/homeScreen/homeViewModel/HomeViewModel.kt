package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.homeViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.dao.Category_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.data.local.room.relations.NoteAndComponents
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.events.HomeScreenEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class HomeViewModel(
    val noteDao: Note_Dao,
    val categoryDao : Category_Dao
) : ViewModel() {


    var notes = mutableStateListOf<Note>()
        private set
    var categories = mutableStateListOf<Category_Room>()
        private set
    var selectedCategoryId by mutableStateOf(0)
        private set

    init {
        getNotes()
        getCategories()
    }


    fun onEvent(even : HomeScreenEvents){
        when(even){
            is HomeScreenEvents.setCategory->{
                setCategory(even.categoryId)
            }
            is HomeScreenEvents.DeleteNote->{
                deleteNote(
                    noteId = even.noteId ,
                    onSuccess = {
                        even.onSuccess()
                    }
                )
            }
            else->{

            }
        }
    }



    private fun setCategory( categoryId : Int){
        selectedCategoryId = categoryId
    }

    private fun deleteNote(noteId : Int , onSuccess : ()->Unit){
        viewModelScope.launch {
//            noteDao.delete(Note_Room(id = noteId ))
            noteDao.deleteWithAllImages(noteId)
            withContext(Dispatchers.Main){
                onSuccess()
            }
        }
    }


    fun getNotes(){

        var noteRelation : Flow<List<NoteAndComponents>> = if(selectedCategoryId == 0) noteDao.getNoteAndComponents() else noteDao.getNoteByCategory(selectedCategoryId)

        viewModelScope.launch {

            var type = 0 ;

            noteRelation.collect{

                var noteList : List<Note>


                noteList = it.map{

                    type = 0

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
                        createdAt  = it.note.createdAt,
                        activateAt = it.note.activateAt,
                        color      = it.note.color,
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


    fun getCategories(){
        viewModelScope.launch {
            categoryDao.getCategories().collect{
                categories.clear()
                categories.addAll(it)
            }
        }
    }

}