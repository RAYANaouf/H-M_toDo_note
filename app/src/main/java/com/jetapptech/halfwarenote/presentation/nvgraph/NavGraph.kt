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
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_background
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white2
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.AddNoteScreen
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.addNoteViewModel.AddNoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.screenData.main
import com.jetapptech.halfwarenote.presentation.view.screen.analiticsScreen.AnaliticsScreen
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.HomeScreen
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.homeViewModel.HomeViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.noteScreen.viewModel.NoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.OnboardingScreen
import com.jetapptech.halfwarenote.presentation.view.screen.parameterScreen.ParameterScreen
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
        startDestination = homeScreen,
        modifier         = modifier
    ) {

        composable<onboardingScreen>{

            SideEffect {
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
                currentScreen(homeScreen)
            }
            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            val homeViewModel = koinViewModel<HomeViewModel>()

            LaunchedEffect(key1 = homeViewModel.selectedCategoryId ) {
                homeViewModel.getNotes()
            }

            HomeScreen(
                notes = homeViewModel.notes,
                categories = homeViewModel.categories,
                onEvent = homeViewModel::onEvent,
                selectedCategoryId = homeViewModel.selectedCategoryId,
                onClick = {screen  ->
                    navHostController
                        .navigate(
                            screen
                        )
                },
                modifier = Modifier
                    .background(custom_background)
            )


        }

        composable<addNoteScreen>{

            SideEffect{
                currentScreen(addNoteScreen())
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            var args = it.toRoute<addNoteScreen>()
            val viewModel : AddNoteViewModel = koinViewModel()


            SideEffect {
                viewModel.getNoteById(args.noteId)
            }


            if (viewModel.scene == main){
                AddNoteScreen(
                    categories = viewModel.categories,
                    note = viewModel.note,
                    onEvent = { event ->
                        viewModel.onEvent(
                            event  = event,
                            onSave = {
                                navHostController.navigate(homeScreen){
                                    popUpTo(homeScreen){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .background(custom_background)
                )
            }
            else{
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(custom_white1)
                ){
                    Text(text = "Saving ...")
                }
            }


        }

        composable<parametersScreen> {
            SideEffect{
                currentScreen(parametersScreen)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )

            ParameterScreen(
                modifier = Modifier
                    .background(custom_background)
            )
        }


        composable<noteScreen>{

            val args = it.toRoute<noteScreen>()

            SideEffect {
                currentScreen(noteScreen(noteId = args.noteId))
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            val noteViewModel = koinViewModel<NoteViewModel>()

            noteViewModel.getNoteById( noteId = args.noteId )

            NoteScreen(
                note = noteViewModel.note,
                modifier = Modifier
                    .background(custom_background)
            )


        }


        composable<lateScreen>{

            SideEffect {
//                onShowBars(true , 4f ,true , 8f)
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