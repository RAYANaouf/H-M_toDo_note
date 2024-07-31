package com.jetapptech.hw_todo_note.presentation.screens.noteScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteMedia
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteTitle
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.Paragraph
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.checkBox
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun NoteScreen(
    note     : Note?,
    modifier : Modifier = Modifier
) {


    Box(
        modifier = modifier
    ) {


        LazyColumn(
            modifier = Modifier
        ) {

            item(key = "header") {
                Row(
                    modifier = Modifier
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 30.dp, top = 16.dp)
                        ) {
                            Text(
                                text = "8/7/2024",
                                style = TextStyles.Monospace_TextStyles.TextStyleSZ9
                            )
                        }

                        Spacer(modifier = Modifier.height(26.dp))

                        Box(
                            modifier = Modifier
                                .padding(start = 30.dp)
                        ) {
                            NoteTitle(
                                noteTitle = note?.title ?: "Loading",
                                onChange = {
//                                    noteTitle = it
                                },
                                enable   = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                    }
                }
            }

            if (note?.components != null){
                itemsIndexed(
                    items = note.components
                ){ index, item_component ->
                    Spacer(modifier = Modifier.height(20.dp))

                    if (item_component is Paragraph){
                        Paragraph(
                            txt = item_component.txt,
                            onChange = {
                                //nothing
                            },
                            hint =  "paragraph ${item_component.index}",
                            enable = false,
                            modifier = Modifier
                                .padding(start = 30.dp , end = 25.dp)
                        )
                    }
                    else if(item_component is CheckBox){
                        checkBox(
                            txt = item_component.txt,
                            onChange = {
                                       //nothing
                            },
                            hint =  "ToDo $index",
                            checked = item_component.checked,
                            oncheck = {
                                      //nothing
                            },
                            enable = false,
                            modifier = Modifier
                                .padding(start = 30.dp , end = 25.dp)
                        )
                    }
                    else if (item_component is Media){
                        NoteMedia(
                            media = item_component,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 25.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = 2.dp,
                                    color = custom_white2,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )
                    }
                }

            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}