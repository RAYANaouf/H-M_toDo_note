package com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.components.charts

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1


@Composable
fun AnaliticsChart(
    modifier: Modifier = Modifier
) {

    val steps = 5

    val pointsData = listOf(
        Point(0f  , 0f),
        Point(1f  , 2f),
        Point(2f  , 5f),
        Point(3f  , 1f),
        Point(4f  , 8f),
        Point(5f  , 7f),
        Point(6f  , 12f),
        Point(7f  , 12f),
        Point(8f  , 12f),
        Point(9f  , 10f),
        Point(10f , 15f),
        Point(11f , 19f),
        Point(12f , 17f),
        Point(13f , 18f),
    )


    val xAxisData = AxisData.Builder()
        .axisStepSize(60.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData {
                i -> (i+1).toString() + "/8"
        }
        .labelAndAxisLinePadding(15.dp)
        .startPadding(120.dp)
        .axisLineColor(p_color0)
        .axisLabelColor(p_color0)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(p_color0)
        .axisLabelColor(p_color0)
        .build()


    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle( color = p_color0, lineType = LineType.SmoothCurve(isDotted = false)),
                    intersectionPoint = IntersectionPoint( color = p_color0),
                    selectionHighlightPoint = SelectionHighlightPoint(color = p_color1),
                    shadowUnderLine = ShadowUnderLine(
                        alpha = 0.6f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                p_color1,
                                Color.Transparent
                            )
                        )
                    ),
                    selectionHighlightPopUp = SelectionHighlightPopUp()
                )
            ),
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
    )


    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )


}