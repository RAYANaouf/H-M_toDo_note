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
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.categoryFilterSection.categoriesFilterSection
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.imgNote.ImgNote
import com.jetapptech.halfwarenote.util.ShakingEffect.ShakingState
import com.jetapptech.halfwarenote.util.ShakingEffect.rememberShackingState
import com.jetapptech.halfwarenote.util.ShakingEffect.shakable
import com.jetapptech.hw_todo_note.presentation.screens.homeScreen.components.NormalNote.NormalNote
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    notes              : List<Note>,
    categories         : List<Category_Room>,
    selectedCategoryId : Int,
    onCategoryClick    : (Int)->Unit = {},
    onClick            : (Int)->Unit = {},
    modifier           : Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(20.dp))


        categoriesFilterSection(
            categories       = categories,
            selectedCategory = selectedCategoryId,
            onCategoryClick  = onCategoryClick,
            modifier         = Modifier
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

            items(
                items = notes,
                key = {
                    it.id
                }
            ){
                if(it.type == 0){
                    val shakingState = rememberShackingState(strength = ShakingState.Strength.Custom(20f))
                    NormalNote(
                        note = it,
                        onClick = {noteId->
//                            onClick(noteId)
                            coroutineScope.launch {
                                shakingState.shake(20)
                            }
                        },
                        modifier = Modifier
                            .shakable(shakingState)
                    )
                }
                else{
                    ImgNote(
                        note = it,
                        onClick = {noteId->
                            onClick(noteId)
                        }
                    )
                }
            }


        }
    }

}