package com.jetapptech.halfwarenote.presentation.nvgraph

import android.app.Activity
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.AddNoteScreen
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.addNoteViewModel.AddNoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.screenData.main
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.AnaliticsScreen
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.HomeScreen
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.homeViewModel.HomeViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.noteScreen.viewModel.NoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.OnboardingScreen
import com.jetapptech.hw_todo_note.presentation.screens.noteScreen.NoteScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    onShowBars       : (Boolean , Float , Boolean , Float)->Unit = {top, top_shadow , bottom , bottom_shadow->},
    currentScreen    : (AppScreen)->Unit = {},
    modifier         : Modifier = Modifier
) {

    NavHost(
        navController    = navHostController,
        startDestination = addNoteScreen,
        modifier         = modifier
    ) {

        composable<onboardingScreen>{

            SideEffect {
                onShowBars(false , 0f ,false , 0f)
                currentScreen(onboardingScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )

            OnboardingScreen(
                onDoneClicked = {
                    navHostController.navigate(homeScreen) {

                    }
                },
                modifier = Modifier
            )

        }

        composable<homeScreen>{


            SideEffect {
                onShowBars(true , 4f ,true , 8f)
                currentScreen(homeScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            val homeViewModel = koinViewModel<HomeViewModel>()
            val context = LocalContext.current


            LaunchedEffect(key1 = homeViewModel.selectedCategoryId ) {
                homeViewModel.getNotes()
            }

            HomeScreen(
                notes = homeViewModel.notes,
                categories = homeViewModel.categories,
                onCategoryClick = {
                    Toast.makeText(context , "$it" , Toast.LENGTH_LONG).show()
                    homeViewModel.setCategory(it)
                },
                selectedCategoryId = homeViewModel.selectedCategoryId,
                onClick = {noteId ->
                    navHostController
                        .navigate(
                            noteScreen(
                                noteId = noteId
                            )
                        )
                },
                modifier = Modifier
                    .background(custom_white1)
            )


        }


        composable<searchScreen>{


            SideEffect {
                onShowBars(true , 4f ,true , 8f)
                currentScreen(searchScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                Text(text = "search screen" )
            }


        }


        composable<noteScreen>{

            val arg = it.toRoute<noteScreen>()

            SideEffect {
                onShowBars(true , 4f ,false , 8f)
                currentScreen(noteScreen(noteId = arg.noteId))
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            val noteViewModel = koinViewModel<NoteViewModel>()

            noteViewModel.getNoteById( noteId = arg.noteId )

            NoteScreen(
                note = noteViewModel.note
            )


        }


        composable<addNoteScreen>{
            SideEffect{
                onShowBars(true , 4f ,true , 8f)
                currentScreen(addNoteScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


//            val context = LocalContext.current


            val viewModel : AddNoteViewModel = koinViewModel()

            if (viewModel.scene == main){
                AddNoteScreen(
                    categories = viewModel.categories,
                    onEvent = {
                        viewModel.onEvent(
                            event  = it,
                            onSave = {
//                                navHostController.navigate(homeScreen){
//                                    popUpTo(homeScreen){
//                                        inclusive = true
//                                    }
//                                }
                            }
                        )
                    },
                    modifier = Modifier
                )
            }
            else{
                Box(modifier = Modifier){
                    Text(text = "Saving ...")
                }
            }


        }

        composable<lateScreen>{

            SideEffect {
                onShowBars(true , 4f ,true , 8f)
                currentScreen(lateScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "late task screen")
            }

        }

        composable<analyticsScreen>{
            SideEffect {
                onShowBars(true , 4f ,true , 8f)
                currentScreen(analyticsScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )

            AnaliticsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(custom_white1)
            )


        }

        composable<aboutUsScreen>{
            SideEffect {
                onShowBars(false , 0f ,false , 0f)
                currentScreen(aboutUsScreen)
            }
            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


        }

    }

}




@Composable
fun set_system_bars_color(
    statusBarColor     : Color,
    lightStatusBar     : Boolean = true,
    navigationBarColor : Color,
    lightNavigationBar : Boolean = true
) {

    val view             = LocalView.current
    val activity = view.context as Activity
    val window   = activity.window

    SideEffect {
        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars     = lightStatusBar
        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = lightNavigationBar
    }


}