package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.sigmasea.util.objects.TextStyles

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun checkBox(
    checked : Boolean = false,
    txt     : String ,
    onChange : (String)->Unit = {},
    oncheck  : ()->Unit = {},
    hint     : String  = "ToDo1",
    enable   : Boolean = true,
    modifier: Modifier = Modifier
) {

    var expanded by remember{
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .combinedClickable(
                onClick = {

                },
                onLongClick = {
                    expanded = true
                }
            )
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(custom_white2)
                .clickable {
                    oncheck()
                }
        ) {
            Image(
                painter = painterResource(id = if(checked) R.drawable.checked else R.drawable.unchecked),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(10.dp))
        
        Box(
            modifier = Modifier
        ) {

            BasicTextField(
                value = txt,
                onValueChange = {
                    onChange(it)
                },
                textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3 , lineHeight = 15.sp),
                enabled = enable
            )

            if (txt == ""){
                Text(
                    text = hint,
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3)
                )
            }
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