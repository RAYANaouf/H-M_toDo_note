package com.jetapptech.halfwarenote.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.jetapptech.halfwarenote.data.local.room.entities.CheckBox_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Paragraph_Room

data class NoteAndComponents(
    @Embedded val note : Note_Room,


    @Relation(
        parentColumn = "id",
        entityColumn = "note_id"
    )
    val paragraphs : List<Paragraph_Room>,


    @Relation(
        parentColumn = "id",
        entityColumn = "note_id"
    )
    val checkBoxs : List<CheckBox_Room>,


    @Relation(
        parentColumn = "id",
        entityColumn = "note_id"
    )
    val media : List<Media_Room>
)