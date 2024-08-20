package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.parseColor
import android.util.Base64
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.presentation.nvgraph.addNoteScreen
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.sigmasea.util.objects.TextStyles
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteMedia(
    media: Media,
    enable   : Boolean = true,
    modifier : Modifier = Modifier
) {


    var expanded by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = modifier
    ) {

        AsyncImage(
            model = File(media.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {

                    },
                    onLongClick = {
                        expanded = true
                    }
                )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .background(
                    color = custom_white1
                )
        ) {

            DropdownMenuItem(
                text = {
                    Text(
                        text     = "Edit",
                        style    = TextStyles.inter_TextStyles.TextStyleSZ6.copy(color = Color(
                            parseColor("#0059FF")
                        )
                        ),
                        modifier = Modifier
                    )
                },
                onClick = {
                    expanded = false
//                    onClick(addNoteScreen( noteId = note.id ))
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text     = "Delete",
                        style    = TextStyles.inter_TextStyles.TextStyleSZ6.copy(color = Color.Red),
                        modifier = Modifier
                    )
                },
                onClick = {
                    expanded = false
//                    onDelete(note.id)
                }
            )

        }

    }

}




