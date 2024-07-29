package com.jetapptech.halfwarenote.presentation.nvgraph

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.AddNoteScreen
import com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.addNoteViewModel.AddNoteViewModel
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.HomeScreen
import com.jetapptech.halfwarenote.presentation.view.screen.onboardingScreen.OnboardingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    onShowBars       : (Boolean , Float , Boolean , Float)->Unit = {top, top_shadow , bottom , bottom_shadow->},
    modifier: Modifier = Modifier
) {

    NavHost(
        navController    = navHostController,
        startDestination = OnboardingScreen,
        modifier         = modifier
    ) {

        composable<OnboardingScreen>{

            LaunchedEffect(key1 = true) {
                onShowBars(false , 0f ,false , 0f)
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


            LaunchedEffect(key1 = true) {
                onShowBars(true , 4f ,true , 8f)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )

            HomeScreen(
                modifier = Modifier
            )


        }



        composable<addNoteScreen>{
            LaunchedEffect(key1 = true) {
                onShowBars(true , 4f ,true , 8f)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


            val context = LocalContext.current


            val viewModel : AddNoteViewModel = koinViewModel()

            AddNoteScreen(
                onEvent = {
                    viewModel.onEvent(
                        event  = it,
                        onSave = {
                            Toast.makeText(context , "hello abdel razak" , Toast.LENGTH_LONG).show()
//                            navGraphState.navigate("home"){
//                                popUpTo("home"){
//                                    inclusive = true
//                                }
//                            }
                        }
                    )
                },
                modifier = Modifier
            )


        }


        composable<analyticsScreen>{
            LaunchedEffect(key1 = true) {
                onShowBars(false , 0f ,false , 0f)
            }

            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


        }

        composable<aboutUsScreen>{
            LaunchedEffect(key1 = true) {
                onShowBars(false , 0f ,false , 0f)
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