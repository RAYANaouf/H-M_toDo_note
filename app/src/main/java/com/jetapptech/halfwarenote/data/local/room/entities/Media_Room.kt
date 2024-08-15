package com.jetapptech.halfwarenote.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Media",
    foreignKeys = [
        ForeignKey(
            entity        = Note_Room::class,
            parentColumns = ["id"],
            childColumns  = ["note_id"],
            onDelete      = ForeignKey.CASCADE
        )
    ]
)
data class Media_Room(
    @PrimaryKey(autoGenerate = true)
    val id      : Int = 0 ,
    val note_id : Long    = 0 ,
    val img     : String = "" ,
    val index   : Int = 0
)