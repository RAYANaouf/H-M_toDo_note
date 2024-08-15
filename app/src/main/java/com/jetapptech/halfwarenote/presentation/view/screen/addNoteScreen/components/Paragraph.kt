package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun Paragraph(
    txt      : String ,
    onChange : (String)->Unit ={},
    enable   : Boolean = true,
    hint     : String ,
    modifier : Modifier = Modifier
) {



    Box(
        modifier = modifier
    ) {

        BasicTextField(
            value = txt,
            onValueChange = {
                onChange(it)
            },
            textStyle = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3),
            enabled = enable
            )

        if(txt == ""){

            Text(
                text  = hint,
                style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3)
            )
        }

    }

}