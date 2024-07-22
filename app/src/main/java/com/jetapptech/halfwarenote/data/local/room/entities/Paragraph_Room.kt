package com.jetapptech.halfwarenote.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Paragraph")
data class Paragraph_Room (
    @PrimaryKey(autoGenerate = true)
    val id      : Int     = 0 ,
    val note_id : Long    = 0 ,
    val txt     : String  = "",
    val index   : Int     = 0
)