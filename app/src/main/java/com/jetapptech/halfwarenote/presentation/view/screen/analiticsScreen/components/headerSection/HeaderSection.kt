package com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.headerSection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.charts.achieved.Acheived
import com.jetapptech.sigmasea.util.objects.TextStyles


@Composable
fun HeaderSection(
    modifier: Modifier = Modifier
) {


    var selectedSlide by remember{
        mutableStateOf(-1)
    }


    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(start = 16.dp, end = 16.dp)
            .background(custom_white0)
            .border(
                width = 2.dp,
                color = custom_white4,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(150.dp)
            .clickable {

            }
    ) {

        Spacer(modifier = Modifier.width(10.dp))

        Acheived(
            selectedSlide = {
                if(it == selectedSlide){
                    selectedSlide = -1
                }
                else{
                    selectedSlide = it
                }
            },
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(if (selectedSlide == 0) 1.2f else 1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(p_color0)
                        .size(16.dp)
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = "Completed tasks",
                    style = TextStyles.inter_TextStyles.TextStyleSZ8.copy(color = custom_black3),
                    modifier = Modifier
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(if (selectedSlide == 1) 1.2f else 1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(p_color1)
                        .size(16.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Waiting tasks",
                    style = TextStyles.inter_TextStyles.TextStyleSZ8.copy(color = custom_black3),
                    modifier = Modifier
                )
            }
            
        }

        Spacer(modifier = Modifier.width(10.dp))

    }




}