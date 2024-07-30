package com.jetapptech.halfwarenote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jetapptech.InAlpha.presentation.view.material.topBar.TopAppBar
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.presentation.nvgraph.NavGraph
import com.jetapptech.halfwarenote.presentation.nvgraph.addNoteScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.analyticsScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.homeScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.lateScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.onboardingScreen
import com.jetapptech.halfwarenote.presentation.nvgraph.searchScreen
import com.jetapptech.halfwarenote.presentation.ui.theme.HalfwareNoteTheme
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.view.materia.bottomBar.BottomAppBar
import com.jetapptech.halfwarenote.presentation.view.materia.navigationDrawer.NavigationDrawer
import com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.HomeScreen
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent() {
            HalfwareNoteTheme() {

                Box(
                    modifier = Modifier
                        .safeDrawingPadding()
                ){
                   MainScreen()
                }


            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val navGraphState = rememberNavController()

    val viewModel = koinViewModel<MainViewModel>()


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AnimatedVisibility(visible = viewModel.show_topbar , modifier = Modifier ) {
                    TopAppBar(
                        title = "H@W Note",
                        img   = R.drawable.logo,
                        elevation = viewModel.topbar_shadow,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = viewModel.show_bottombar , modifier = Modifier ) {
                BottomAppBar(
                    selected = when( viewModel.current_screen ) {
                        homeScreen ->{
                            0
                        }
                        searchScreen ->{
                            1
                        }
                        addNoteScreen ->{
                            2
                        }
                        lateScreen ->{
                            3
                        }
                        analyticsScreen ->{
                            4
                        }
                        else -> {
                            0
                        }
                    } ,
                    onClick = {
                        if(it == 0){
                            if (viewModel.current_screen == homeScreen)
                                return@BottomAppBar
                            navGraphState.navigate(homeScreen){

                            }
                        }
                        else if(it == 1){
                            if (viewModel.current_screen == searchScreen)
                                return@BottomAppBar
                            navGraphState.navigate(searchScreen){

                            }
                        }
                        else if(it == 2){
                            if (viewModel.current_screen == addNoteScreen)
                                return@BottomAppBar
                            navGraphState.navigate(addNoteScreen){

                            }
                        }
                        else if(it == 3){
                            if (viewModel.current_screen == lateScreen)
                                return@BottomAppBar
                            navGraphState.navigate(lateScreen){

                            }
                        }
                        else{
                            if (viewModel.current_screen == analyticsScreen)
                                return@BottomAppBar
                            navGraphState.navigate(analyticsScreen){

                            }
                        }

                    },
                    elevation = viewModel.bottombar_shadow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }

        },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->

        NavGraph(
            navHostController = navGraphState,
            onShowBars = {topbar , topbar_shadow , bottombar , bottombar_shadow ->
                if(topbar != viewModel.show_topbar || topbar_shadow.dp != viewModel.topbar_shadow)
                    viewModel.setTopBar(show = topbar , shadow = topbar_shadow)
                if(bottombar != viewModel.show_bottombar || bottombar_shadow.dp != viewModel.bottombar_shadow)
                    viewModel.setBottomBar(show = bottombar , shadow = bottombar_shadow)
            },
            currentScreen = {appScreen ->
                viewModel.setCurrentScreen(appScreen)
            },
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        )

    }

}