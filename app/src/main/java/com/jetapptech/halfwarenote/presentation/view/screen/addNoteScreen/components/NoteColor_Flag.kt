package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteColor_Flag(
    color   : Color,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(30.dp)
            .border(
                width = 5.dp,
                color = color,
                shape = CircleShape
            )
    )

}