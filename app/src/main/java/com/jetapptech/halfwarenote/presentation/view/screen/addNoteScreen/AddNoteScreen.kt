package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.CheckBox
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.NoteComponent
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.data.local.room.relations.NoteAndComponents
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteColor_Flag
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteMedia
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.NoteTitle
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.Paragraph
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.ToolBar
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.checkBox
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.dialogs.CategoryDialog
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.dialogs.ColorPickerDialog
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.dialogs.PasswordDialog
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.events.AddNoteEvents
import com.jetapptech.sigmasea.util.objects.TextStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.Date

@Composable
fun AddNoteScreen(
    modifier   : Modifier = Modifier,
    note       : NoteAndComponents? = null,
    categories : List<Category_Room>,
    onEvent    : (AddNoteEvents)->Unit = {}
) {


    //***************** vars *********************//
    val context = LocalContext.current

    var components  = remember {
        mutableStateListOf<NoteComponent>()
    }
    var noteTitle  by remember {
        mutableStateOf(note?.note?.title ?: "")
    }

    var noteColor by remember {
        mutableStateOf(note?.note?.color ?: Color.Cyan.toArgb())
    }

    var notePassword by remember {
        mutableStateOf(note?.note?.password ?: "")
    }

    var noteHint by remember {
        mutableStateOf(note?.note?.hint ?: "")
    }

    var show_colorPicker by remember {
        mutableStateOf(false)
    }

    var show_passwordDialog by remember {
        mutableStateOf(false)
    }
    var show_categoryDialog by remember {
        mutableStateOf(false)
    }

    var selectedCategoryId by remember {
        mutableStateOf(0)
    }

//    var index by remember {
//        //because the 0 is the first paragraph in the note which is required
//        mutableIntStateOf(
//            if (note == null){
//                0
//            }
//            else{
//                Toast.makeText(context , "${note.media.size + note.paragraphs.size + note.checkBoxs.size}" , Toast.LENGTH_LONG).show()
//                note.media.size + note.paragraphs.size + note.checkBoxs.size
//            }
//        )
//    }



    val coroutineScope = rememberCoroutineScope()




    //*************  dialogs  ***********************//

    ColorPickerDialog(
        show = show_colorPicker,
        onDismiss = {
            show_colorPicker = false
        },
        onDone = {
            noteColor = it.toArgb()
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

    CategoryDialog(
        show       = show_categoryDialog,
        categories = categories,
        onDismiss  = {
            show_categoryDialog = false
        },
        onDone     = {
            selectedCategoryId = it.id
        },
        onCreate = {
            onEvent(AddNoteEvents.createCategory(it.category))
        },
        selectedCategory = selectedCategoryId,
        modifier   = Modifier
            .fillMaxWidth()
    )




    //************ launchers ******************//
    val getImage = rememberLauncherForActivityResult(contract =  ActivityResultContracts.PickVisualMedia()){uri->
        if (uri != null){

            coroutineScope.launch(Dispatchers.IO) {

                try {

                    val inputStream: InputStream = context.contentResolver.openInputStream(uri) ?: return@launch

                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val file = File(context.filesDir, "fileName_${Date().time}" )
                    FileOutputStream(file).use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    }
                    components.add(Media(img = file.absolutePath , index = components.size))


                }
                catch (e : Exception){
                    Toast.makeText(context , e.message , Toast.LENGTH_LONG).show()
                }
            }


        }

    }


    LaunchedEffect(key1 = note) {
        noteTitle = note?.note?.title ?: ""
        val arrayComponents = ArrayList<NoteComponent>()
        if (note?.media != null){
            arrayComponents.addAll(note.media.map      { Media    ( id = it.id , img = it.img , index = it.index ) })
            arrayComponents.addAll(note.checkBoxs.map  { CheckBox ( id = it.id , txt = it.txt , checked = it.checked , index = it.index ) })
            arrayComponents.addAll(note.paragraphs.map { Paragraph( id = it.id , txt = it.txt , index = it.index ) })
        }
        order(components = arrayComponents)
        components.clear()
        components.addAll(arrayComponents)
    }


    // UI




    Box(
        modifier = modifier
    ){

        NoteColor_Flag(
            color = Color(noteColor),
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
//                        Box(
//                            modifier = Modifier
//                                .padding(start = 30.dp , top = 16.dp)
//                        ) {
//                            Text(
//                                text = "8/7/2024",
//                                style = TextStyles.Monospace_TextStyles.TextStyleSZ9
//                            )
//                        }

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
                        enable = (note == null),
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
                        enable = (note == null),
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
                        enable = true,
                        onDelete = {


                            if (note == null)
                                components.remove(item_component)
                            else{
                                onEvent(AddNoteEvents.deleteImage(item_component.id , item_component.img))
                                components.remove(item_component)
                            }
                        },
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
                        components.add(Paragraph(index = components.size))
                    }
                    "check_box"->{
                        components.add(CheckBox(index = components.size))
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
                        val note_room : Note_Room
                        if (note == null){
                            note_room = Note_Room(title = noteTitle , color = noteColor , category_id = selectedCategoryId , password = notePassword , hint = noteHint)
                            onEvent(AddNoteEvents.saveNote(note_room , components))
                        }
                        else{
                            note_room = Note_Room( id = note.note.id, title = noteTitle , color = noteColor , category_id = selectedCategoryId , password = notePassword , hint = noteHint)
                            onEvent(AddNoteEvents.editNote(note_room , components))
                        }

                    }
                    "lock"->{
                        show_passwordDialog = true
                    }
                    "category"->{
                        show_categoryDialog = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )



    }

}


fun order(components : ArrayList<NoteComponent>){

    for (i in 0 .. components.size-1){

        for (j in i .. components.size-1){
            if (components[i]._index > components[j]._index ){
                var buffer = components[i]
                components[i] = components[j]
                components[j] = buffer
            }
        }

    }

}