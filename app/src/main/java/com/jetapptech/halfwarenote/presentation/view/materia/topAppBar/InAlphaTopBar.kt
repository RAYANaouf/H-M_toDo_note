package com.jetapptech.InAlpha.presentation.view.material.topBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.sigmasea.util.objects.TextStyles


@Composable
fun TopAppBar(
    title            : String = "",
    @DrawableRes img : Int? = null,
    elevation : Dp = 1.dp,
    modifier  : Modifier = Modifier,
) {

    Surface(
        shadowElevation = elevation,
        color    = custom_white0 ,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.width(24.dp))

            if(img != null){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .size(40.dp)
                ){
                    Image(
                        painter = painterResource(id = img) ,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = p_color0,
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            if(title != ""){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxHeight()
                ){
                    Text(
                        text = title,
                        style    = TextStyles.Monospace_TextStyles.TextStyleSZ4.copy(color = p_color1  , fontFamily = FontFamily(Font(R.raw.copperplate_light)) ,  fontWeight = FontWeight(800),),
                        modifier = Modifier
                    )
                }
            }


            Spacer(modifier = Modifier
                .weight(1f)
            )


            Box(
                modifier = Modifier
                    .size(40.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.menu) ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

        }
    }

}
