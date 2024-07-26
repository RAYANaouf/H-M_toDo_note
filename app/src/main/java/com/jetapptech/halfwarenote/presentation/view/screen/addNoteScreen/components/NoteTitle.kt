package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black1
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun NoteTitle(
    noteTitle : String = "",
    onChange  : (String)->Unit = {} ,
    enable    : Boolean = true,
    modifier  : Modifier
) {



    val constraints = ConstraintSet {
        val text = createRefFor("text")
        val hint = createRefFor("hint")
        val underLine = createRefFor("underLine")

        constrain(text){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }

        constrain(hint){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }

        constrain(underLine){
            top.linkTo(text.bottom)

            if(noteTitle == ""){
                start.linkTo(hint.start)
                end.linkTo(hint.end)

                width = Dimension.fillToConstraints
            }
            else{
                start.linkTo(text.start)
                end.linkTo(text.end)

                width = Dimension.fillToConstraints
            }

        }

    }


    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier
    ) {

        BasicTextField(
            value = noteTitle,
            onValueChange = {
                onChange(it)
            },
            enabled   = enable,
            textStyle = TextStyles.Itim_TextStyles.TextStyleSZ2.copy(color = custom_black1),
            modifier  = Modifier
                .layoutId("text")
                .wrapContentWidth()
        )

        if(noteTitle == ""){
            Text(
                text      = "Note's title",
                style     = TextStyles.Itim_TextStyles.TextStyleSZ2.copy(color = custom_black1),
                modifier  = Modifier
                    .layoutId("hint")
            )
        }



        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(custom_black1)
                .layoutId("underLine")
        )

    }
}