package com.marcelodev.projetopascoa.feature.config

import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.marcelodev.projetopascoa.R
import com.marcelodev.projetopascoa.components.habitListener.HabitListener
import com.marcelodev.projetopascoa.components.options.Options
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState.OnCloseMaps
import com.marcelodev.projetopascoa.feature.config.ConfigViewModelEventState.OnGoBackMenu
import com.marcelodev.projetopascoa.ui.theme.Background
import com.marcelodev.projetopascoa.ui.theme.BackgroundHeader
import com.marcelodev.projetopascoa.ui.theme.BorderDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConfigRoute() {
    val viewModel: ConfigViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ConfigRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun ConfigRoute(
    uiState: ConfigViewModelUiState,
    onEvent: (ConfigViewModelEventState) -> Unit,
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    check(uiState is ConfigViewModelUiState.Steps) {
        "ConfigRoute: uiState must be of type ConfigViewModelUiState.Steps"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // EGG FOUND
        AnimatedVisibility(!uiState.isFistHabitListenerDialog) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundHeader)
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Ovos encontrados ${uiState.eggsFound}/18",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BorderDialog,
                        modifier = Modifier.padding(8.dp)
                    )

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.raw.eggs)
                                .build(),
                            imageLoader = imageLoader
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            PlayAudioOnStart()

            // MAPA
            AnimatedVisibility(uiState.optionSelected != null && !uiState.isMaps) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .clickable { onEvent(ConfigViewModelEventState.OnOpenMaps) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.maps),
                            contentDescription = "Descrição da imagem",
                            modifier = Modifier
                                .size(40.dp)
                        )

                        Text(
                            text = "Mapa",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = BorderDialog,
                        )
                    }
                }
            }

            AnimatedVisibility(uiState.isMaps) {
                BackHandler {
                    onEvent(OnCloseMaps)
                }

                MapScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.1f)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                )
            }

            // OPTIONS
            AnimatedVisibility(uiState.isMenuHabitListenerDialog && !uiState.isMaps) {
                // CHALLENGERS
                Options(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    optionsList = uiState.data.options,
                    onEvent = onEvent,
                )
            }

            // COLLECT EGGS
            AnimatedVisibility(!uiState.isMenuHabitListenerDialog && !uiState.isFistHabitListenerDialog && !uiState.isMaps) {
                CollectEggs(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .weight(0.6f),
                    imageLoader = imageLoader,
                    uiState = uiState,
                    onEvent = onEvent,
                )
            }

            // DIALOG WITH HABIT LISTENER
            AnimatedVisibility(!uiState.isMaps) {
                HabitListener(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(end = 16.dp, top = 16.dp),
                    gifUrl = uiState.habitListenerGif,
                    message = uiState.habitListenerMessage,
                    showNextMessage = uiState.isFistHabitListenerDialog,
                    onClick = { onEvent(ConfigViewModelEventState.OnClickHabitListener) },
                )
            }
        }
    }
}

@Composable
fun CollectEggs(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    uiState: ConfigViewModelUiState.Steps,
    onEvent: (ConfigViewModelEventState) -> Unit,
) {
    BackHandler {
        onEvent(OnGoBackMenu)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.raw.eggs)
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier.size(170.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onEvent(ConfigViewModelEventState.OnCompletedOption(uiState.optionSelected!!))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BorderDialog,
                contentColor = Color.White,
            ),
            border = BorderStroke(2.dp, BorderDialog),
        ) {
            Text(
                text = "Encontrei o ovo! 🥚",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun PlayAudioOnStart() {
    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(context, R.raw.pascoa).apply {
        isLooping = true
    }

    LaunchedEffect(Unit) {
        mediaPlayer.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}

@Composable
fun MapScreen(
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    val transformableState = remember {
        TransformableState { zoomChange, panChange, _ ->
            scale *= zoomChange
            offsetX += panChange.x
            offsetY += panChange.y
        }
    }

    Image(
        painter = painterResource(id = R.drawable.maps_image),
        contentDescription = "Mapa com zoom e arrasto",
        modifier = modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offsetX,
                translationY = offsetY
            )
            .transformable(state = transformableState)
    )
}

@Preview
@Composable
fun ConfigRoutePreview() {
    ConfigRoute()
}