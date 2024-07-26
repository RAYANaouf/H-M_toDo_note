package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white5


@Composable
fun Category(
    category : String,
    modifier: Modifier = Modifier
) {

    var selected by remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) custom_white3 else custom_white2)
            .border(
                width = 2.dp,
                color = if (selected) custom_white5 else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .height(40.dp)
            .clickable {
                selected = !selected
            }
            .padding(start = 6.dp, end = 6.dp)
    ){
        Text(
            text = category
        )
    }

}