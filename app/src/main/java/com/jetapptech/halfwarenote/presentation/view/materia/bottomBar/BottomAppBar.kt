package com.jetapptech.halfwarenote.presentation.view.materia.bottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0

@Composable
fun BottomAppBar(
    selected  : Int = 0,
    onClick   : (Int)->Unit = {},
    elevation : Dp = 6.dp,
    modifier: Modifier = Modifier
) {

    val lottie_composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.adding_lottie))
    val progress by animateLottieCompositionAsState(composition = lottie_composition , isPlaying = true , iterations = Int.MAX_VALUE)


    Surface(
        shadowElevation = elevation,
        color = custom_white0,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(0)
                    }
                    .drawBehind {
                        drawLine(
                            color = if(selected == 0) p_color0 else Color.Transparent,
                            strokeWidth = if(selected == 0) 5.dp.toPx() else 0.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(25.dp)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(1)
                    }
                    .drawBehind {
                        drawLine(
                            color = if(selected == 1) p_color0 else Color.Transparent,
                            strokeWidth = if(selected == 1) 5.dp.toPx() else 0.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(25.dp)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(2)
                    }
                    .drawBehind {
                        drawLine(
                            color = if(selected == 2) p_color0 else Color.Transparent,
                            strokeWidth = if(selected == 2) 5.dp.toPx() else 0.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    }
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable),
//                    contentDescription = null,
//                    contentScale = ContentScale.Inside,
//                    modifier = Modifier
//                        .size(45.dp)
//                )
                LottieAnimation(
                    composition = lottie_composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(45.dp)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(3)
                    }
                    .drawBehind {
                        drawLine(
                            color = if(selected == 3) p_color0 else Color.Transparent,
                            strokeWidth = if(selected == 3 ) 5.dp.toPx() else 0.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.late),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(25.dp)
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable {
                        onClick(4)
                    }
                    .drawBehind {
                        drawLine(
                            color = if(selected == 4) p_color0 else Color.Transparent,
                            strokeWidth = if(selected == 4) 5.dp.toPx() else 0.dp.toPx(),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.analitics),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(25.dp)
                )
            }


        }
    }

}