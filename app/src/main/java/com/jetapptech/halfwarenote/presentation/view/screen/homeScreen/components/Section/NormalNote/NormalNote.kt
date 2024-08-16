package com.jetapptech.hw_todo_note.presentation.screens.homeScreen.components.NormalNote

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.presentation.nvgraph.AppScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.addNoteScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.noteScreen
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white5
import com.jetapptech.halfwarenote.util.ShakingEffect.ShakingState
import com.jetapptech.halfwarenote.util.ShakingEffect.rememberShackingState
import com.jetapptech.halfwarenote.util.ShakingEffect.shakable
import com.jetapptech.sigmasea.util.objects.TextStyles
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NormalNote(
    note         : Note = Note(),
    onClick      : (AppScreen)->Unit = {},
    onLongClick  : (Int)->Unit = {},
    onDelete     : (Int)->Unit = {},
    background   : Color         = Color(0xFFFFFFFF),
    padding      : PaddingValues = PaddingValues(start = 12.dp , top = 10.dp , bottom = 10.dp , end = 12.dp),
    modifier     : Modifier        = Modifier
) {


    /**************  vars  ***************/
    //effect var
    val shakingState = rememberShackingState(
        strength = ShakingState.Strength.Custom(15f),
    )

    //compose var
    val coroutineScope = rememberCoroutineScope()

    //logic var
    var expanded by remember{
        mutableStateOf(false)
    }


    /***************************     user interface      ***************************/

    Surface(
        shadowElevation = 2.dp,
        color = background,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.5.dp,
            color = Color(0xFFD0D0D0),
        ),
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .shakable(shakingState)
            .combinedClickable(
                onClick = {
                    onClick(noteScreen( editable = false , noteId = note.id))
                },
                onLongClick = {
                    expanded = true
                    onLongClick(note.id)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = note.title,
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = custom_black1),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width((16+4+4).dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(16.dp)
                            .border(width = 2.dp, color = Color(note.color), shape = CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
            ) {
                Text(
                    text = note.createdAt.toString(),
                    style = TextStyles.HintTextStyles.TextStyleSZ9,
                    maxLines = 1,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
            ) {
                if (note.components.size == 0){
                    return@Box
                }
                Text(
                    text = (note.components[0] as Paragraph).txt,
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3),
                    maxLines = 3,
                    modifier = Modifier
                )
            }


            Spacer(modifier = Modifier.height(4.dp))


        }


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
                        style    = TextStyles.inter_TextStyles.TextStyleSZ6.copy(color = custom_white5),
                        modifier = Modifier
                    )
                },
                onClick = {
                    expanded = false
                    onClick(addNoteScreen( noteId = note.id ))
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
                    onDelete(note.id)
                }
            )

        }

    }

}