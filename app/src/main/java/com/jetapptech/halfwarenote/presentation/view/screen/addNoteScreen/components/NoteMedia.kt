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
import com.jetapptech.halfwarenote.data.local.dataClasses.Media

@Composable
fun NoteMedia(
    media: Media,
    modifier : Modifier = Modifier
) {



    Box(
        modifier = modifier
    ) {
        Image(
            bitmap = stringToBitmap(media.img).asImageBitmap() ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}


fun stringToBitmap(img : String): Bitmap{
    val img_bytes = Base64.decode(img , Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(img_bytes , 0 , img_bytes.size)
}


