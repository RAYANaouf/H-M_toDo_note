package com.jetapptech.halfwarenote

import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jetapptech.InAlpha.presentation.view.material.topBar.TopAppBar
import com.jetapptech.halfwarenote.presentation.nvgraph.NavGraph
import com.jetapptech.halfwarenote.presentation.ui.theme.HalfwareNoteTheme
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
import com.jetapptech.halfwarenote.presentation.view.materia.bottomBar.BottomAppBar
import com.jetapptech.halfwarenote.presentation.view.materia.navigationDrawer.NavigationDrawer

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

    val viewModel = MainViewModel()


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
                            .fillMaxWidth().height(55.dp)
                    )
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = viewModel.show_bottombar , modifier = Modifier ) {
                BottomAppBar(
                    elevation = viewModel.bottombar_shadow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
            }

        },
        floatingActionButton = {

        },
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->

        NavGraph(
            navHostController = navGraphState,
            onShowBars = {topbar , topbar_shadow , bottombar , bottombar_shadow ->
                viewModel.setTopBar(show = topbar , shadow = topbar_shadow)
                viewModel.setBottomBar(show = bottombar , shadow = bottombar_shadow)
            },
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        )

    }

}