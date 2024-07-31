package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components.dialogs

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white8
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun ColorPickerDialog(
    show      : Boolean,
    onDone    : (Color)->Unit,
    onDismiss : ()->Unit,
    modifier  : Modifier = Modifier
) {


    if(show){

        val controller = rememberColorPickerController()
        var my_color : Color = Color.White


        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {

            Column(
                modifier = modifier
            ) {

                Box(
                    contentAlignment = Alignment.Center,
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
                    Text(
                        text = "Pick a color",
                        style = TextStyles.Itim_TextStyles.TextStyleSZ1.copy(color = p_color0)
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    AlphaTile(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 1.dp,
                                color = custom_white4,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        controller = controller
                    )
                }


                Spacer(modifier = Modifier.height(30.dp))


                HsvColorPicker(
                    controller = controller,
                    onColorChanged = {
                        my_color = it.color
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp)
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
                                onDone(my_color)
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