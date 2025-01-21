package com.example.feedtherabbit.presenter.views.viewgroup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feedtherabbit.R
import com.example.feedtherabbit.presenter.view_model.MainViewModel
import com.example.feedtherabbit.presenter.views.element.RabbitView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeRootView(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onStartClicked: (rabbit: String, musicIcon: String) -> Unit
) {
    with(sharedTransitionScope) {

        val playingMusicState = mainViewModel?.musicPlayingState?.collectAsState()

        Box(modifier = modifier) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .width(290.dp)
                        .height(290.dp),
                    painter = painterResource(R.drawable.title),
                    contentDescription = "title",
                )
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onStartClicked("rabbit", "music_icon")
                        },
                    painter = painterResource(R.drawable.start_png),
                    contentDescription = "start"
                )
                Spacer(Modifier.fillMaxHeight(0.1f))
                RabbitView(
                    modifier = Modifier
                        .height(360.dp)
                        .width(360.dp)
                        .sharedElement(
                            state = rememberSharedContentState("rabbit"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000, delayMillis = 200)
                            }
                        )
                )
            }

            Image(
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomStart)
                    .sharedElement(
                        state = rememberSharedContentState("music_icon"),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000, delayMillis = 200)
                        })
                    .clickable {
                        if (playingMusicState?.value == true) {
                            mainViewModel.stopMusic()
                        } else {
                            mainViewModel?.playMusic()
                        }
                    },
                painter = painterResource(
                    if (playingMusicState?.value == true) R.drawable.stop_png else R.drawable.play_png
                ),
                contentDescription = "start"
            )
        }
    }
}

//@OptIn(ExperimentalSharedTransitionApi::class)
//@Preview(showSystemUi = true)
//@Composable
//private fun RootViewPreview() {
//    SaveTheBirdTheme {
//        HomeRootView(
//            Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .paint(
//                    painterResource(R.drawable.ms2),
//                    contentScale = ContentScale.FillBounds
//                ),
//            sharedTransitionScope = SharedTransitionScope()
//            null
//        ) {}
//    }
//}