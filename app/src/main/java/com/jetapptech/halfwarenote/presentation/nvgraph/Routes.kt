package com.jetapptech.halfwarenote.presentation.nvgraph

import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import kotlinx.serialization.Serializable



@Serializable
open class AppScreen

@Serializable
object onboardingScreen : AppScreen()


@Serializable
object homeScreen : AppScreen()

@Serializable
class addNoteScreen(val noteId : String) : AppScreen()

@Serializable
object lateScreen : AppScreen()

@Serializable
object analyticsScreen : AppScreen()

@Serializable
object parametersScreen : AppScreen()



@Serializable
class noteScreen( val editable : Boolean = false , val noteId : Int = 1 ) : AppScreen()


@Serializable
object aboutUsScreen : AppScreen()


