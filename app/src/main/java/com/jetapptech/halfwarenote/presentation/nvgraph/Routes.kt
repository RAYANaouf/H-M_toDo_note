package com.jetapptech.halfwarenote.presentation.nvgraph

import kotlinx.serialization.Serializable



@Serializable
open class AppScreen

@Serializable
object onboardingScreen : AppScreen()


@Serializable
object homeScreen : AppScreen()

@Serializable
object searchScreen : AppScreen()

@Serializable
object addNoteScreen : AppScreen()


@Serializable
object lateScreen : AppScreen()

@Serializable
object analyticsScreen : AppScreen()

@Serializable
object aboutUsScreen : AppScreen()


