package com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black5
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white5
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white6
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white7
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun Header(
    scene : Int,
    modifier: Modifier = Modifier
) {


    val lottie_composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(if(scene == 0 )R.raw.lottie1 else if(scene == 1) R.raw.lottie2 else if(scene == 2) R.raw.lottie3 else if(scene == 3) R.raw.lottie4 else R.raw.lottie2 ))
    val progress by animateLottieCompositionAsState(composition = lottie_composition , isPlaying = true , iterations = Int.MAX_VALUE)

    Column(
        modifier = modifier
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
//                .background(Color.Cyan)
        ) {
            LottieAnimation(
                composition = lottie_composition ,
                progress = {
                    progress
                } ,
                modifier = Modifier
                    .size(if(scene == 0) 350.dp else 250.dp)
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
//                .background(Color.Gray)
        ) {

            Text(
                text  = if( scene == 0 ) "Welcoming"  else if( scene == 1 ) "To do list" else if( scene == 2 ) "Insert Images "  else if( scene == 3 ) "Powerful Search" else "Note" ,
                style = TextStyles.HintTextStyles.TextStyleSZ2.copy(color = p_color0 ,  fontWeight = FontWeight(800) , textAlign = TextAlign.Center)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text =
                if( scene == 0 )
                    "We are thrilled to introduce our latest note-taking application, packed with a host of new and innovative features. designed to enhance your productivity and organization."
                else if(scene == 1)
                    "Easily add to-do lists within your notes to keep your tasks and thoughts in one place. Check off items as you complete them and stay on top of your schedule effortlessly."
                else if(scene == 2)
                    "Enhance your notes by adding images directly. Whether it's photos, screenshots, or drawings, you can easily insert and organize images to make your notes more visually appealing and informative."
                else if(scene == 3)
                    "Find what you need in an instant with our powerful search feature. Simply type in the note title, content, or keywords to quickly locate your notes and stay organized.\n"
                else
                    "Enhance your notes by adding images directly. Whether it's photos, screenshots, or drawings, you can easily insert and organize images to make your notes more visually appealing and informative.",
                style = TextStyles.Monospace_TextStyles.TextStyleSZ10.copy(color = custom_white6 , textAlign = TextAlign.Center , lineHeight = 20.sp),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            )
        }


    }

}