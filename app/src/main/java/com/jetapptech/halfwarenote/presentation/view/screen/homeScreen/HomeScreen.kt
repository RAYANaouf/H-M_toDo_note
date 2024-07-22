package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.Section.categoryFilterSection.categoriesFilterSection
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.Section.imgNote.ImgNote
import com.jetapptech.hw_todo_note.presentation.screens.homeScreen.components.NormalNote.NormalNote


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .background(custom_white1)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        categoriesFilterSection(
            modifier = Modifier
        )


        Spacer(modifier = Modifier.height(25.dp))

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 12.dp,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .height(1000.dp)
        ) {

            items(1){
                ImgNote(
                    note = Note(title = "image note")
                )
            }

            items(1){
                NormalNote()
            }
            items(1){
                ImgNote()
            }

            items(1){
                NormalNote()
            }
            items(10){
                ImgNote()
            }

            items(10){
                NormalNote()
            }


        }
    }

}