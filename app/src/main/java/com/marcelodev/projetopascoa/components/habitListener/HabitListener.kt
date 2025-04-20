package com.marcelodev.projetopascoa.components.habitListener

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.marcelodev.projetopascoa.ui.theme.BackgroundDialog
import com.marcelodev.projetopascoa.ui.theme.BorderDialog

@Composable
fun HabitListener(
    modifier: Modifier = Modifier,
    gifUrl: Int,
    message: String,
    showNextMessage: Boolean = false,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .background(BackgroundDialog, shape = RoundedCornerShape(8.dp))
                    .border(2.dp, BorderDialog, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
            ) {
                Text(
                    text = message,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = BorderDialog
                )
            }

            if (showNextMessage) {
                Text(
                    text = "Clique para continuar",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = BorderDialog
                )
            }
        }

        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(GifDecoder.Factory())
            }
            .build()

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(gifUrl)
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}