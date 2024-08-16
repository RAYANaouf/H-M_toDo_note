package com.jetapptech.halfwarenote.data.local.room.entities
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Note")
data class Note_Room(
    @PrimaryKey(autoGenerate = true)
    val id           : Int     = 0,
    val title        : String  = "",
    val category_id  : Int     = 0,
    val createdAt    : Long    = Date().time,
    val activateAt   : Long    = Date().time,
    val color        : Int     = 0,
    val password     : String  = "",
    val hint         : String  = ""
)

