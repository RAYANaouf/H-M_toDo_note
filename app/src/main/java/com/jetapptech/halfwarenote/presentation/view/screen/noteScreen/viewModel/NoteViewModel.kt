package com.jetapptech.halfwarenote.presentation.view.screen.noteScreen.viewModel

import androidx.compose.runtime.getValue
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
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import kotlinx.coroutines.launch
import java.util.Date



class NoteViewModel  constructor(
    private val noteDao: Note_Dao
): ViewModel() {


    var note : Note? by mutableStateOf(null)


    fun getNoteById(noteId : Int) {

        viewModelScope.launch {
            val noteAndComponents = noteDao.getNoteById(noteId)

            val paragraphs : List<Paragraph> = noteAndComponents.paragraphs.map { Paragraph(id = it.id , txt = it.txt , index = it.index) }
            val media      : List<Media>     = noteAndComponents.media.map { Media(id = it.id , img = it.img , index = it.index ) }
            val checkBoxs  : List<CheckBox>  = noteAndComponents.checkBoxs.map { CheckBox(id = it.id , txt = it.txt , checked = it.checked , index = it.index) }

            var components = ArrayList<NoteComponent>()
            components.addAll(paragraphs)
            components.addAll(media)
            components.addAll(checkBoxs)

            order(components)

            note =  Note(
                id = noteAndComponents.note.id,
                title = noteAndComponents.note.title,
                activateAt = Date(noteAndComponents.note.activateAt),
                createdAt = Date(noteAndComponents.note.createdAt),
                color = Color(noteAndComponents.note.color),
                password = noteAndComponents.note.password,
                hint     = noteAndComponents.note.hint,
                components = components
            )
        }

    }


}





fun order(components : ArrayList<NoteComponent>){

    for (i in 0 .. components.size-1){

        for (j in i .. components.size-1){
            if (components[i]._index > components[j]._index ){
                var buffer = components[i]
                components[i] = components[j]
                components[j] = buffer
            }
        }

    }

}