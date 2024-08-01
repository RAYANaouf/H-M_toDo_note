package com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.charts.mainChart.AnaliticsChart
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.headerSection.HeaderSection
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun AnaliticsScreen(
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ){

        Spacer(modifier = Modifier.height(25.dp))

        HeaderSection(
            modifier = Modifier
                .fillMaxWidth()
//                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = "Daily task completion" ,
            style = TextStyles.Itim_TextStyles.TextStyleSZ2.copy(color = p_color0),
            modifier = Modifier
                .padding(start = 16.dp , end = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AnaliticsChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(custom_white0),
        )

        Spacer(modifier = Modifier.height(25.dp))

    }
}