package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.presentation.nvgraph.AppScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.noteScreen
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white4
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.categoryFilterSection.categoriesFilterSection
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.imgNote.ImgNote
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.events.HomeScreenEvents
import com.jetapptech.halfwarenote.util.ShakingEffect.ShakingState
import com.jetapptech.halfwarenote.util.ShakingEffect.rememberShackingState
import com.jetapptech.halfwarenote.util.ShakingEffect.shakable
import com.jetapptech.hw_todo_note.presentation.screens.homeScreen.components.NormalNote.NormalNote
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    notes              : List<Note>,
    onEvent            : (HomeScreenEvents)-> Unit = {},
    categories         : List<Category_Room>,
    selectedCategoryId : Int,
    onClick            : (AppScreen)->Unit = {screen -> },
    modifier           : Modifier = Modifier
) {


    var selectedNote_LongPress by remember{
        mutableIntStateOf(-1)
    }


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(20.dp))


        categoriesFilterSection(
            categories       = categories,
            selectedCategory = selectedCategoryId,
            onCategoryClick  = {categoryId->
                onEvent(HomeScreenEvents.setCategory(categoryId))
            },
            modifier         = Modifier
        )


        Spacer(modifier = Modifier.height(25.dp))

        LazyVerticalStaggeredGrid(
            columns               = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing   = 12.dp,
            modifier              = Modifier
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
                    NormalNote(
                        note = it,
                        onClick = {screen->
                            onClick(screen)
                        },
                        onLongClick = {noteid->
                            selectedNote_LongPress = noteid
                        },
                        onDelete    = {noteId->
                            onEvent(HomeScreenEvents.DeleteNote(noteId = noteId))
                        },
                        modifier = Modifier
                    )
                }

                else{
                    ImgNote(
                        note = it,
                        onClick = {screen->
                            onClick(screen)
                        },
                        onLongClick = {
                            selectedNote_LongPress = it
                        },
                        onDelete    = {noteId->
                            onEvent(HomeScreenEvents.DeleteNote(noteId = noteId))
                        },
                        modifier = Modifier
                    )
                }

            }


        }
    }

}