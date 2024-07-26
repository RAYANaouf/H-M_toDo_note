package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.events

import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room


sealed class AddNoteEvents{

    class saveNoten(val note: Note_Room, val components : List<NoteComponent>) : AddNoteEvents()

}
