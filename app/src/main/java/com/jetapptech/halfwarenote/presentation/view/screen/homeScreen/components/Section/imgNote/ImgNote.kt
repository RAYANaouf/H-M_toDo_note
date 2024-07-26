package com.jetapptech.halfwarenote.presentation.view.screen.homeScreen.components.Section.imgNote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jetapptech.halfwarenote.data.local.dataClasses.Media
import com.jetapptech.halfwarenote.data.local.dataClasses.Note
import com.jetapptech.halfwarenote.data.local.dataClasses.Paragraph
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black1
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_black3
import com.jetapptech.halfwarenote.presentation.ui.theme.custom_white1
import com.jetapptech.sigmasea.util.objects.TextStyles

@Composable
fun ImgNote(
    note       : Note = Note(),
    onClick    : (Int)->Unit = {},
    background : Color         = Color(0xFFFFFFFF),
    modifier   : Modifier = Modifier
) {


    val context = LocalContext.current

    var img by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true ) {
        note.components.forEach {
            if (it is Media){
                img = it.img
                return@forEach
            }
        }
    }

    Surface(
        shadowElevation = 2.dp,
        color = background,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.5.dp,
            color = Color(0xFFD0D0D0),
        ),
        modifier = modifier
            .clickable {
                onClick(note.id)
//                Toast.makeText(context , "node Id : ${note.id} \n ${note.components.size}"  , Toast.LENGTH_LONG).show()
            }
    ) {
        Column(
            modifier = Modifier
        ) {


            if ( img != "" ){
                Image(
                    bitmap = stringToBitmap(img).asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = note.title,
                        style = TextStyles.Monospace_TextStyles.TextStyleSZ6.copy(color = custom_black1),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width((16+4+4).dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(16.dp)
                            .border(width = 2.dp, color = note.color, shape = CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .padding( start = 12.dp , end = 12.dp)
            ) {
                Text(
                    text = note.createdAt.time.toString(),
                    style = TextStyles.HintTextStyles.TextStyleSZ9,
                    maxLines = 1,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .padding( start = 12.dp , end = 12.dp)
            ) {
                if (note.components.size == 0){
                    return@Box
                }
                Text(
                    text = (note.components[0] as Paragraph).txt,
                    style = TextStyles.Monospace_TextStyles.TextStyleSZ9.copy(color = custom_black3),
                    maxLines = 3,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

        }
    }

}



fun stringToBitmap(img : String): Bitmap {
    val img_bytes = Base64.decode(img , Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(img_bytes , 0 , img_bytes.size)
}