package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen

import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteColor_Flag
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteMedia
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteTitle
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.Paragraph
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.ToolBar
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.checkBox
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.dialogs.ColorPickerDialog.ColorPickerDialog
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.dialogs.PasswordDialog
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.events.AddNoteEvents
import com.jetapptech.sigmasea.util.objects.TextStyles
import java.io.ByteArrayOutputStream

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    onEvent  : (AddNoteEvents)->Unit = {}
) {



    //***************** vars *********************//

    var components  = remember {
        mutableStateListOf<NoteComponent>(Paragraph(index = 0))
    }
    var noteTitle  by remember {
        mutableStateOf("")
    }

    var noteColor by remember {
        mutableStateOf(Color.White)
    }

    var notePassword by remember {
        mutableStateOf("")
    }

    var noteHint by remember {
        mutableStateOf("")
    }

    var show_colorPicker by remember {
        mutableStateOf(false)
    }

    var show_passwordDialog by remember {
        mutableStateOf(false)
    }

    var index by remember {
        //because the 0 is the first paragraph in the note which is required
        mutableStateOf(0+1)
    }






    //*************  dialogs  ***********************//

    ColorPickerDialog(
        show = show_colorPicker,
        onDismiss = {
            show_colorPicker = false
        },
        onDone = {
            noteColor = it
            show_colorPicker = false
        },
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.75f)
            .clip(RoundedCornerShape(12.dp))
            .background(custom_white0)
    )


    PasswordDialog(
        show = show_passwordDialog,
        onDismiss = {
            show_passwordDialog = false
        },
        onDone = {password , hint ->
            notePassword = password
            noteHint     = hint
            show_passwordDialog = false
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(custom_white0)
    )




    //************ launchers ******************//
    val getImage = rememberLauncherForActivityResult(contract =  ActivityResultContracts.PickVisualMedia()){url->
        if (url != null){


//            var inputStream = context.contentResolver.openInputStream(url)
//            val byteArrayOutputStream = ByteArrayOutputStream()
//
//            inputStream?.use { stream->
//                val buffer = ByteArray(1024)
//                var bytesread : Int
//
//                bytesread = stream.read(buffer)
//                while (bytesread != -1){
//                    byteArrayOutputStream.write(buffer , 0 , bytesread)
//                    bytesread = stream.read(buffer)
//                }
//
//                var img_byteArray = byteArrayOutputStream.toByteArray()
//                var img_string    = Base64.encodeToString(img_byteArray, Base64.DEFAULT)
//                components.add(Media(img = img_string , index = index++))
////                Toast.makeText(content , "$img_string" , Toast.LENGTH_LONG).show()
//            }


        }

    }


    // UI




    Box(
        modifier = modifier
    ){

        NoteColor_Flag(
            color = noteColor,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
        )




        LazyColumn(
            modifier = Modifier
        ){

            item(key = "header"){
                Row(
                    modifier = Modifier
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 30.dp , top = 16.dp)
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
                                } ,
                                modifier  = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                    }
                }
            }

            itemsIndexed(
                items = components
            ){ index, item_component ->
                Spacer(modifier = Modifier.height(20.dp))

                if (item_component is Paragraph){
                    Paragraph(
                        txt = item_component.txt,
                        onChange = {
                            components[index] = Paragraph(id =  item_component.id , txt = it , index = item_component.index )
                        },
                        hint =  "paragraph ${item_component.index}",
                        modifier = Modifier
                            .padding(start = 30.dp , end = 25.dp)
                    )
                }
                else if(item_component is CheckBox){
                    checkBox(
                        txt = item_component.txt,
                        onChange = {
                            components[index] = CheckBox(id =  item_component.id , txt = it , checked = item_component.checked , index = item_component.index )
                        },
                        hint =  "ToDo $index",
                        checked = item_component.checked,
                        oncheck = {
                            components[index] = CheckBox(id =  item_component.id , txt = item_component.txt , checked = !item_component.checked ,  index = item_component.index )

                        },
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

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }


        }




        ToolBar(
            onClick = {event->
                when(event){
                    "paragraph"->{
                        components.add(Paragraph(index = index++))
                    }
                    "check_box"->{
                        components.add(CheckBox(index = index++))
                    }
                    "gallery"->{
                        getImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                    "peindre"->{
                        show_colorPicker = !show_colorPicker
                    }
                    "alarm"->{

                    }
                    "done"->{
                        val note_room = Note_Room(title = noteTitle , color = noteColor.toArgb() , password = notePassword , hint = noteHint)
                        onEvent(AddNoteEvents.saveNoten(note_room , components))
                    }
                    "lock"->{
                        show_passwordDialog = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )



    }

}