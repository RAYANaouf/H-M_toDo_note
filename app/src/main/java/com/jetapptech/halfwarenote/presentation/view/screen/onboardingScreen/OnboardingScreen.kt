package com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white7
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.buttonsSection.ButtonsSection
import com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.header.Header
import com.jetapptech.sigmasea.util.objects.TextStyles
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onDoneClicked : ()-> Unit = {},
    modifier: Modifier = Modifier
) {


    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(custom_white0)
    ) {

        var pagerState = rememberPagerState {
            4
        }



        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(45.dp)
                    .clickable {

                    }
                    .padding(start = 12.dp, end = 12.dp)

            ) {
                Text(
                    text = "Skip",
                    style = TextStyles.ButtonTextStyles.TextStyleSZ6.copy(color = custom_white7)
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            Header(
                scene = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxSize()
            )

        }



        ButtonsSection(
            scene = pagerState.currentPage,
            onNextClick = {
                coroutineScope.launch {
                    if ( pagerState.currentPage < pagerState.pageCount-1) {
                        pagerState.animateScrollToPage(pagerState.currentPage+1)
                    }
                }
            },
            onDoneClick = {
                onDoneClicked()
            },
            onBackButtonClick = {
                coroutineScope.launch {
                    if ( pagerState.currentPage > 0){
                        pagerState.animateScrollToPage(pagerState.currentPage-1)
                    }
                }
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        )

    }

}