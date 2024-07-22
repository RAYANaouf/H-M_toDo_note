package com.jetapptech.halfwarenote.data.local.room.entities
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Note")
data class Note_Room(
    @PrimaryKey(autoGenerate = true)
    val id         : Int     = 0,
    val title      : String  = "",
    val createdAt  : String  = "1/1/2025",
    val activateAt : String  = "1/12/2025",
    val color      : Int     = 0,
    val password   : String  = "",
    val hint       : String  = ""
)

