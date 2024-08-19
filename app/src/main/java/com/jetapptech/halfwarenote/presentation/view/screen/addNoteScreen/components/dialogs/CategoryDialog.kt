package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.dialogs

import android.graphics.Paint.Align
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.data.local.room.dao.Category_Dao
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black5
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black7
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white5
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white6
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white8
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.sigmasea.util.objects.TextStyles


@Composable
fun CategoryDialog(
    categories : List<Category_Room> = emptyList(),
    selectedCategory : Int ,
    show       : Boolean,
    onDismiss  : ()->Unit,
    onDone     : (Category_Room)->Unit,
    onCreate   : (Category_Room)->Unit,
    modifier   : Modifier = Modifier
) {


    if(show){


        var create by remember {
            mutableStateOf(false)
        }
        var category by remember {
            mutableStateOf("")
        }

        Dialog(
            onDismissRequest = { onDismiss() }
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.5.dp,
                        color = p_color0,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(custom_white0)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = custom_black5,
                                strokeWidth = 2f,
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, y = 0f)
                            )
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.categories),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Categories",
                        style = TextStyles.inter_TextStyles.TextStyleSZ3.copy(color = custom_black3)
                    )
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    categories.forEachIndexed { index, categoryRoom ->

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .background(if (categoryRoom.id == selectedCategory) custom_white3 else custom_white0)
                                .clickable {
                                    onDone(categoryRoom)
                                }
                        ) {
                            Text(text = categoryRoom.category)
                        }

                    }
                }


                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
//                        .background(custom_white0)
                        .clickable {
                            create = !create
                        }
                ) {
                    Text(
                        text = "Create Category",
                        style = TextStyles.inter_TextStyles.TextStyleSZ6.copy(color = custom_black3)
                    )
                }



                if(create){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))

                        OutlinedTextField(
                            value = category ,
                            onValueChange = {
                                category = it
                            },
                            label = {
                                Text(text = "New category")
                            },
                            colors = TextFieldDefaults.colors().copy(focusedIndicatorColor = p_color0 , focusedLabelColor = p_color0 , focusedContainerColor = custom_white0 , unfocusedContainerColor = custom_white0)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .clickable {
                                        onDismiss()
                                    }
                            ) {
                                Text(
                                    text = "Cancel",
                                    style = TextStyles.inter_TextStyles.TextStyleSZ5.copy(color = custom_white6)
                                )
                            }

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .clickable {
                                        onCreate(Category_Room(category = category))
                                        category = ""
                                        create = false
//                                onDismiss()
                                    }
                            ) {
                                Text(
                                    text = "Create",
                                    style = TextStyles.inter_TextStyles.TextStyleSZ5.copy(color = p_color0 )
                                )
                            }
                        }
                    }
                }


            }


        }
    }


}