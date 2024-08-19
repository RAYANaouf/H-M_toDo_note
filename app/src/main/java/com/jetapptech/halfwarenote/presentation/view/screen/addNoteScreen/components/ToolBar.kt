package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.qamar.elasticview.ElasticState
import com.qamar.elasticview.ElasticView
import com.qamar.elasticview.elasticEffect

@Composable
fun ToolBar(
    onClick : (String)->Unit = {},
    modifier : Modifier = Modifier
) {


    val constraints = ConstraintSet {
        var tool      = createRefFor("tool")
        var lock      = createRefFor("lock")
        var done      = createRefFor("done")
        var category  = createRefFor("category")

        constrain(tool){
            start.linkTo(parent.start   , margin = 16.dp)
            end.linkTo(done.start       , margin = 16.dp)
            bottom.linkTo(parent.bottom , margin = 16.dp)

            width = Dimension.fillToConstraints
            height = Dimension.value(50.dp)
        }

        constrain(done){
            start.linkTo(tool.end)
            end.linkTo(parent.end , margin = 16.dp)
            bottom.linkTo(tool.bottom)

            width  = Dimension.value(50.dp)
            height = Dimension.value(50.dp)
        }

        constrain(lock){
            start.linkTo(done.start)
            end.linkTo(done.end)
            bottom.linkTo(done.top , margin = 16.dp)


            width  = Dimension.value(50.dp)
            height = Dimension.value(50.dp)

        }

        constrain(category){
            start.linkTo(done.start)
            end.linkTo(done.end)
            bottom.linkTo(lock.top , margin = 16.dp)


            width  = Dimension.value(50.dp)
            height = Dimension.value(50.dp)
        }


    }


    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .layoutId("tool")
                .height(40.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(custom_white2)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(custom_white2)
                    .clickable {
                        onClick("paragraph")
                    }
            ) {
                Image(
                    painter  = painterResource(id = R.drawable.text_zone),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .background(custom_white2)
                    .clickable {
                        onClick("check_box")
                    }
            ) {
                Image(
                    painter  = painterResource(id = R.drawable.check_box),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(custom_white2)
                    .clickable {
                        onClick("gallery")
                    }
            ) {
                Image(
                    painter  = painterResource(id = R.drawable.galerie),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .background(custom_white2)
                    .clickable {
                        onClick("peindre")
                    }
            ) {
                Image(
                    painter  = painterResource(id = R.drawable.peindre),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier =
//                Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
//                    .clip(CircleShape)
//                    .background(custom_white2)
//                    .clickable {
//                        onClick("alarm")
//                    }
//            ) {
//                Image(
//                    painter  = painterResource(id = R.drawable.alarme),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(30.dp)
//                )
//            }
        }




        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(custom_white2)
                .layoutId("lock")
                .clickable {
                    onClick("lock")
                }
        ) {
            Image(
                painter  = painterResource(id = R.drawable.cadenas),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(custom_white2)
                .layoutId("done")
                .clickable {
                    onClick("done")
                }
        ) {
            Image(
                painter  = painterResource(id = R.drawable.done),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(custom_white2)
                .layoutId("category")
                .clickable {
                    onClick("category")
                }
        ) {
            Image(
                painter  = painterResource(id = R.drawable.categories),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        }

    }



}