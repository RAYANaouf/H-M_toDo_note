package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.categoryFilterSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.R
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.Category

@Composable
fun categoriesFilterSection(
    categories : List<Category_Room>,
    selectedCategory : Int,
    onCategoryClick : (Int)->Unit = {},
    modifier: Modifier = Modifier
) {



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.width(20.dp))

            Category(
                category = "All",
                selected = if(selectedCategory == 0) true else false ,
                onclick = {
                    onCategoryClick(0)
                }
            )

            Spacer(modifier = Modifier.width( 12.dp ))


            categories.forEachIndexed { index, categoryRoom ->

                Category(
                    category = categoryRoom.category,
                    selected = if(selectedCategory == categoryRoom.id) true else false ,
                    onclick = {
                        onCategoryClick(categoryRoom.id)
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.width(if(index == categories.size-1) 20.dp else 12.dp))

            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .width(55.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(20.dp)
            )

        }



    }

}