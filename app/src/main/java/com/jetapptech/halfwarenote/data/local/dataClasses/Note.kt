package com.jetapptech.halfwarenote.data.local.dataClasses

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
data class Note(
    val id         : Int     = 0,
    val title      : String  = "noteTitle",
    val components : List<NoteComponent> = emptyList(),
    val createdAt  : Long    = Date().time,
    val activateAt : Long    = Date().time,
    val type       : Int     = 0,
    val color      : Int     = Color.Cyan.toArgb(),
    val password   : String  = "",
    val hint       : String  = ""
)




interface NoteComponent{

    var _index   : Int
}

data class Paragraph (
    val id      : Int     = 0 ,
    val txt     : String  = "",
    val index   : Int     = 0
): NoteComponent {
    override var _index: Int = index
}

data class CheckBox (
    val id      : Int    = 0,
    val txt     : String = "",
    var checked : Boolean = false,
    val index   : Int     = 0
): NoteComponent {
    override var _index: Int = index
}

data class Media(
    val id      : Int  = 0,
    val img     : String = "",
    val index   : Int     = 0
): NoteComponent {
    override var _index: Int = index
}