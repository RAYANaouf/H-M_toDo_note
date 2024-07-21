package com.jetapptech.halfwarenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jetapptech.halfwarenote.presentation.nvgraph.NavGraph
import com.jetapptech.halfwarenote.presentation.ui.theme.HalfwareNoteTheme
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white0
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


    NavigationDrawer(
        modifier = Modifier
            .background(color = custom_white0)
    ){
        Scaffold(
            topBar = {

            },
            bottomBar = {

            },
            floatingActionButton = {

            }
        ) {paddingValues ->

            NavGraph(
                navGraphState = navGraphState,
                paddingValues = paddingValues,
                onShowBars = {topbar , topbar_shadow , bottombar , bottombar_shadow ->

                },
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

        }
    }

}