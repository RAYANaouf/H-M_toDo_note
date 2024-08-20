package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.events

import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room


sealed class AddNoteEvents{

    class saveNote(val note: Note_Room, val components : List<NoteComponent>) : AddNoteEvents()
    class editNote(val note: Note_Room, val components : List<NoteComponent>) : AddNoteEvents()
    class deleteImage(val imageId: Int) : AddNoteEvents()
    class createCategory(val category: String) : AddNoteEvents()

}
