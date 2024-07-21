package com.jetapptech.halfwarenote.presentation.nvgraph

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0

@Composable
fun NavGraph(
    navGraphState: NavHostController,
    paddingValues: PaddingValues,
    onShowBars       : (Boolean , Int , Boolean , Int)->Unit = {top, top_shadow , bottom , bottom_shadow->},
    modifier: Modifier = Modifier
) {

    NavHost(
        navController    = navGraphState,
        startDestination = "OnboardingScreen",
        modifier         = modifier
    ) {

        composable(
            route = "OnboardingScreen"
        ){
            onShowBars(false , 0 ,false , 0)
            set_system_bars_color(
                statusBarColor     = custom_white0,
                lightStatusBar     = true,
                navigationBarColor = custom_white0,
                lightNavigationBar = false
            )


        }
//
//        composable<homeScreen>{
//            onShowBars(true , 4 ,true , 4)
//            set_system_bars_color(
//                statusBarColor     = custom_white0,
//                lightStatusBar     = true,
//                navigationBarColor = custom_white0,
//                lightNavigationBar = false
//            )
//
//
//        }
//
//        composable<analyticsScreen>{
//            onShowBars(false , 0 ,false , 0)
//            set_system_bars_color(
//                statusBarColor     = custom_white0,
//                lightStatusBar     = true,
//                navigationBarColor = custom_white0,
//                lightNavigationBar = false
//            )
//
//
//        }
//
//        composable<aboutUsScreen>{
//            onShowBars(false , 0 ,false , 0)
//            set_system_bars_color(
//                statusBarColor     = custom_white0,
//                lightStatusBar     = true,
//                navigationBarColor = custom_white0,
//                lightNavigationBar = false
//            )
//
//
//        }

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