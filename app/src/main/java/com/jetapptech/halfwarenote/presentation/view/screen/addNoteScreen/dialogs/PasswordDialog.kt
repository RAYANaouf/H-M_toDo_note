package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white8
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.sigmasea.util.objects.TextStyles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordDialog(
    show      : Boolean,
    onDismiss : ()->Unit ,
    onDone    : (String , String)->Unit ,
    modifier  : Modifier = Modifier
) {


    if(show){

        var hint by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }

        Dialog(
            onDismissRequest = { onDismiss}
        ) {

            Column(
                modifier = modifier
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .drawBehind {
                            drawLine(
                                color = custom_white4,
                                strokeWidth = 2.dp.toPx(),
                                start = Offset(x = 0f, y = size.height),
                                end = Offset(x = size.width, y = size.height)
                            )
                        }
                ) {

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.password),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Password",
                            style = TextStyles.Itim_TextStyles.TextStyleSZ2.copy(color = p_color0)
                        )
                    }

                }


                Spacer(modifier = Modifier.height(30.dp))


                //*****************  worning   *****************/
                Box(modifier = Modifier.padding(start = 25.dp , end = 25.dp)){

                    Text(
                        text = "Your hint should clearly refer to the password in a way only you would understand.",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = p_color0 ),
                        overflow = TextOverflow.Clip
                    )

                }



                Spacer(modifier = Modifier.height(50.dp))



                Box(modifier = Modifier.padding(start = 25.dp , end = 25.dp)){
                    Text(
                        text = "enter a hint to remember the password" ,
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9
                    )
                }

                OutlinedTextField(
                    value = hint,
                    onValueChange = {
                        hint = it
                    },
                    label = {
                        Text(text = "Hint")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = p_color0,
                        focusedBorderColor = p_color0,
                        focusedLabelColor = p_color0,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 25.dp, end = 25.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))


                Box(modifier = Modifier.padding(start = 25.dp , end = 25.dp)){

                    Text(
                        text = "enter the password",
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ9
                    )

                }
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text(text = "Password")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = p_color0,
                        focusedBorderColor = p_color0,
                        focusedLabelColor = p_color0,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(start = 25.dp, end = 25.dp)
                )


                Spacer(modifier = Modifier.height(50.dp))


                //**********   buttons  ************/

                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = custom_white4,
                                strokeWidth = 2.dp.toPx(),
                                start = Offset(x = 0f, y = 0f),
                                end = Offset(x = size.width, y = 0f)
                            )
                        }
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
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ5.copy(color = custom_white8)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .drawBehind {
                                drawLine(
                                    color = custom_white4,
                                    strokeWidth = 2.dp.toPx(),
                                    start = Offset(x = 0f, y = 0f),
                                    end = Offset(x = 0f, y = size.height)
                                )
                            }
                            .clickable {
                                onDone(password , hint)
                            }
                    ) {
                        Text(
                            text = "Done",
                            style = TextStyles.Monospace_TextStyles.TextStyleSZ5.copy(color = Color.Blue)
                        )
                    }

                }
            }

        }
    }


}