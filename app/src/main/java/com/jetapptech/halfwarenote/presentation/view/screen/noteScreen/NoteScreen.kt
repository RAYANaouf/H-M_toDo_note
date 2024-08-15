package com.jetapptech.hw_todo_note.presentation.screens.noteScreen

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.entities.Paragraph_Room
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteMedia
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteTitle
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.Paragraph
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.checkBox
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun NoteScreen(
    note     : Note?,
    editable : Boolean = false,
    modifier : Modifier = Modifier
) {


    //***************** vars *********************//

    var context = LocalContext.current


    var components  = remember {
        mutableStateListOf<NoteComponent>()
    }

    var noteTitle  by remember {
        mutableStateOf(note?.title ?: "")
    }

    var noteColor by remember {
        mutableStateOf(Color.White)
    }

    //********************* effects **************************//

    LaunchedEffect(key1 = note?.components) {
//        Toast.makeText(context , "it change" , Toast.LENGTH_LONG).show()
        if(note?.components != null){
            components.clear()
            components.addAll(note.components)
        }
    }




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
                                noteTitle = noteTitle,
                                onChange  = {
                                    noteTitle = it
                                },
                                enable    = editable,
                                modifier  = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                    }
                }
            }

            if (components != null){
                itemsIndexed(
                    items = components
                ){ index, item_component ->
                    Spacer(modifier = Modifier.height(20.dp))

                    if (item_component is Paragraph){
                        Paragraph(
                            txt = item_component.txt,
                            onChange = { new_txt->
                                components.removeAt(index)
                                components.add(index , Paragraph(id = item_component.id , txt = new_txt , index = item_component.index) )
                            },
                            hint =  "paragraph ${item_component.index}",
                            enable = editable,
                            modifier = Modifier
                                .padding(start = 30.dp , end = 25.dp)
                        )
                    }
                    else if(item_component is CheckBox){
                        checkBox(
                            txt = item_component.txt,
                            onChange = {new_txt->
                                components.removeAt(index)
                                components.add(index , CheckBox(id = item_component.id , checked = item_component.checked ,txt = new_txt , index = item_component.index) )
                            },
                            hint =  "ToDo $index",
                            checked = item_component.checked,
                            oncheck = {
                                components.removeAt(index)
                                components.add(index , CheckBox(id = item_component.id , checked = !item_component.checked , txt = item_component.txt , index = item_component.index) )
                            },
                            enable = editable,
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