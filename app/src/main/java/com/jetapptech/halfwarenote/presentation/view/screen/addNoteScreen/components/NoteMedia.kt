package com.jetapptech.halfwarenote.presentation.view.screen.addNoteScreen.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import java.io.File

@Composable
fun NoteMedia(
    media: Media,
    modifier : Modifier = Modifier
) {



    Box(
        modifier = modifier
    ) {
//        Image(
//            bitmap = stringToBitmap(media.img).asImageBitmap() ,
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//        )


        AsyncImage(
            model = File(media.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}




