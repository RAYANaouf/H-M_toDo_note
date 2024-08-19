package com.jetapptech.halfwarenote.presentation.view.screen.parameterScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white7
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color0
import com.jetapptech.halfwarenote.presentation.ui.theme.p_color1
import com.jetapptech.sigmasea.util.objects.TextStyles


@Composable
fun ParameterScreen(
//    language : ,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = TextStyles.Itim_TextStyles.TextStyleSZ1.copy(color = p_color0).toSpanStyle()
                    ){
                        append("S")
                    }

                    withStyle(
                        style = TextStyles.Itim_TextStyles.TextStyleSZ1.copy(color = p_color1).toSpanStyle()
                    ){
                        append("etting")
                    }

                }
            )
        }


        /**************** language   ******************************/
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = TextStyles.Itim_TextStyles.TextStyleSZ4.copy(color = custom_black3).toSpanStyle()
                    ){
                        append("Language")
                    }

                }
            )
        }

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 32.dp, end = 16.dp)
        ) {
            Text(
                text = "English",
                style = TextStyles.inter_TextStyles.TextStyleSZ5
            )
        }


        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 32.dp, end = 16.dp)
        ) {
            Text(
                text = "Arabic",
                style = TextStyles.inter_TextStyles.TextStyleSZ5
            )
        }


        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 32.dp, end = 16.dp)
        ) {
            Text(
                text = "French",
                style = TextStyles.inter_TextStyles.TextStyleSZ5
            )
        }

        /*******************   theme   ******************************/

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = TextStyles.Itim_TextStyles.TextStyleSZ4.copy(color = custom_black3).toSpanStyle()
                    ){
                        append("Theme")
                    }

                }
            )
        }

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 32.dp, end = 16.dp)
        ) {
            Text(
                text = "Light",
                style = TextStyles.inter_TextStyles.TextStyleSZ5
            )
        }

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 32.dp, end = 16.dp)
        ) {
            Text(
                text = "Dark",
                style = TextStyles.inter_TextStyles.TextStyleSZ5
            )
        }


        /*********************  about Us  **********************/

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = TextStyles.Itim_TextStyles.TextStyleSZ4.copy(color = custom_black3).toSpanStyle()
                    ){
                        append("About us")
                    }

                }
            )
        }
        
        Box(modifier = Modifier.height(100.dp))
    }

}