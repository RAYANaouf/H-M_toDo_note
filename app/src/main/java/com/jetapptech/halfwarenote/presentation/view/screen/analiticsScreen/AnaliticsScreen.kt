package com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.chart.AnaliticsChart

@Composable
fun AnaliticsScreen(
    modifier: Modifier = Modifier
) {


    Column(modifier = modifier){

        Spacer(modifier = Modifier.height(25.dp))

        AnaliticsChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )

        Spacer(modifier = Modifier.height(25.dp))

    }
}