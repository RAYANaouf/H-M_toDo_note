package com.jetapptech.halfwarenote.presentation.view.materia.navigationDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight()
            ) {

            }
        }
    ) {

        content()

    }

}