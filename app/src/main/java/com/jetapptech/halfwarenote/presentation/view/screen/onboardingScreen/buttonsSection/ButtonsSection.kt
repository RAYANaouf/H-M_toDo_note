package com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.buttonsSection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black4
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black7
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white7
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.sigmasea.util.objects.TextStyles
import java.time.format.TextStyle


@Composable
fun ButtonsSection(
    scene    : Int,
    onNextClick : () -> Unit ,
    onDoneClick : () -> Unit ,
    onBackButtonClick : () -> Unit ,
    modifier : Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.width(20.dp))


        AnimatedVisibility(visible = scene > 0 ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
//                .background(custom_white3)
                    .height(45.dp)
                    .clickable {
                        onBackButtonClick()
                    }
                    .padding(start = 12.dp, end = 12.dp)

            ) {
                Text(
                    text = "Back",
                    style = TextStyles.ButtonTextStyles.TextStyleSZ6.copy(color = custom_black7)
                )
            }
        }


        if (scene == 3){
            Spacer(modifier = Modifier.width(25.dp))
        }
        else{
            Spacer(modifier = Modifier.weight(1f))
        }


        AnimatedVisibility(visible = scene != 3) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
//                .background(p_color0)
                    .height(45.dp)
                    .clickable {
                        onNextClick()
                    }
                    .padding(start = 12.dp, end = 12.dp)

            ) {
                Text(
                    text = "Next" ,
                    style = TextStyles.ButtonTextStyles.TextStyleSZ6.copy(color = p_color0)
                )
            }
        }



        AnimatedVisibility(
            visible = scene == 3,
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                .background(p_color0)
                    .height(45.dp)
                    .fillMaxWidth()
                    .clickable {
                        onDoneClick()
                    }
                    .padding(start = 12.dp, end = 12.dp)

            ) {
                Text(
                    text =  "Done",
                    style = TextStyles.ButtonTextStyles.TextStyleSZ6.copy(color = custom_white0 , textAlign = TextAlign.Center)
                )
            }
        }


        Spacer(modifier = Modifier.width(25.dp))

    }

}