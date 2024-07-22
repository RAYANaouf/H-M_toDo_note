package com.jetapptech.halfwarenote.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Media")
data class Media_Room(
    @PrimaryKey(autoGenerate = true)
    val id      : Int = 0 ,
    val note_id : Long    = 0 ,
    val img     : String = "" ,
    val index   : Int = 0
)