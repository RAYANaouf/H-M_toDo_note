package com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.charts.achieved

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1


@Composable
fun Acheived(
    selectedSlide : (Int)->Unit = {},
    modifier: Modifier = Modifier
) {

    val data = PieChartData(
        slices = listOf(
            PieChartData.Slice(
                label = "",
                value = 3f,
                color = p_color1
            ),
            PieChartData.Slice(
                label = "",
                value = 7f,
                color = p_color0
            )
        ),
        plotType = PlotType.Pie
    )

    val config = PieChartConfig()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        PieChart(
            pieChartData = data,
            pieChartConfig =config,
            onSliceClick = {i ->
                selectedSlide(if(i.color == p_color0) 0 else 1)
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        )
    }

}