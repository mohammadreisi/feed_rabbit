package com.example.feedtherabbit.presenter.views.viewgroup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.feedtherabbit.R
import com.example.feedtherabbit.business.model.Carrot
import com.example.feedtherabbit.business.model.Line
import com.example.feedtherabbit.business.model.Rabbit
import com.example.feedtherabbit.logic.CARROT_HEIGHT
import com.example.feedtherabbit.logic.CARROT_WIDTH
import com.example.feedtherabbit.logic.RABBIT_WIDTH
import com.example.feedtherabbit.presenter.view_model.MainViewModel
import com.example.feedtherabbit.presenter.views.element.LineView
import com.example.feedtherabbit.presenter.views.element.RabbitView


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GameRootView(
    modifier: Modifier = Modifier,
    rabbitSharedElementName: String,
    musicIconSharedElementName: String,
    characterNumber: Int,
    mainViewModel: MainViewModel?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {

    DisposableEffect(mainViewModel) {
        onDispose {
            mainViewModel?.stopCarrotFalling()
        }
    }

    with(sharedTransitionScope) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp
        val screenWidth = configuration.screenWidthDp
        LaunchedEffect(mainViewModel) {
            mainViewModel?.startCarrotFalling(screenHeight = screenHeight)
        }

        val playingMusicState = mainViewModel?.musicPlayingState?.collectAsState()

        val rabbit = mainViewModel?.rabbitPosition?.observeAsState(Rabbit().apply {
            setLeftX(
                getRabbitLeftX(
                    screenWidth / 4,
                    screenWidth / 2
                )
            )
        })

        val carrot = mainViewModel?.carrotPosition?.observeAsState(Carrot().apply {
            YTop = -(2 * CARROT_HEIGHT)
            linePosition = 0
        })

        val rabbitX by animateDpAsState(
            targetValue = rabbit?.value?.getLeftX()?.dp!!,
            label = "offset"
        )

//        val carrotY by animateDpAsState(
//            targetValue = carrot?.value?.YTop?.dp!!,
//            label = "offset"
//        )

        Box(
            modifier = modifier
        ) {

            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                LineView(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenWidth / 4).dp)
                        .background(Color.Transparent),
                    line = Line(leftX = 0, rightX = screenWidth / 4)

                ) { left, right ->
                    mainViewModel?.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getRabbitLeftX(
                                left,
                                right
                            )
                        )
                    })
                }
                LineView(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenWidth / 4).dp)
                        .background(Color.Transparent),
                    line = Line(leftX = screenWidth / 4, rightX = screenWidth / 2)


                ) { left, right ->
                    mainViewModel?.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getRabbitLeftX(
                                left,
                                right
                            )
                        )
                    })
                }
                LineView(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenWidth / 4).dp)
                        .background(Color.Transparent),
                    line = Line(leftX = screenWidth / 2, rightX = ((screenWidth * 3) / 4))

                ) { left, right ->
                    mainViewModel?.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getRabbitLeftX(
                                left,
                                right
                            )
                        )
                    })
                }
                LineView(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenWidth / 4).dp)
                        .background(Color.Transparent),
                    line = Line(leftX = ((screenWidth * 3) / 4), rightX = screenWidth)

                ) { _, _ -> }
            }

            RabbitView(
                modifier = Modifier
                    .offset(
                        x = rabbitX,
                        y = (screenHeight * 0.75).dp
                    )
                    .height(RABBIT_WIDTH.dp)
                    .width(RABBIT_WIDTH.dp)
                    .sharedElement(
                        state = rememberSharedContentState(rabbitSharedElementName),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000, delayMillis = 200)
                        }
                    ),
                characterNumber = characterNumber
            )

            Image(
                modifier = Modifier
                    .padding(36.dp)
                    .align(Alignment.TopStart)
                    .sharedElement(
                        state = rememberSharedContentState(musicIconSharedElementName),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000, delayMillis = 200)
                        })
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        if (playingMusicState?.value == true) {
                            mainViewModel.stopMusic()
                        } else {
                            mainViewModel?.playMusic()
                        }
                    },
                painter = painterResource(
                    if (playingMusicState?.value == true) R.drawable.stop_png else R.drawable.play_png
                ),
                contentDescription = "music"
            )

            Image(
                modifier = Modifier
                    .absoluteOffset(
                        x = getCarrotLeftX(carrot?.value, screenWidth).dp,
                        y = carrot?.value?.YTop!!.dp
                    )
                    .height(CARROT_HEIGHT.dp)
                    .width(CARROT_WIDTH.dp),
                painter = painterResource(R.drawable.carrot_png),
                contentDescription = "carrot"
            )

        }
    }
}

fun getCarrotLeftX(carrot: Carrot?, screenWidth: Int): Int {
    return when (carrot?.linePosition ?: -1) {

        0 -> ((0 + screenWidth / 4) / 2) - (CARROT_WIDTH / 2)

        1 -> ((screenWidth / 4 + screenWidth / 2) / 2) - (CARROT_WIDTH / 2)

        2 -> ((screenWidth / 2 + (screenWidth * 3) / 4) / 2) - (CARROT_WIDTH / 2)

        else -> 0

    }
}

fun getRabbitLeftX(left: Int, right: Int): Int {
    return ((left + right) / 2) - ((RABBIT_WIDTH / 2))
}


//@Preview(showSystemUi = true)
//@Composable
//private fun Preview() {
//    GameRootView(
//        Modifier
//            .fillMaxSize()
//            .padding(8.dp)
//    ) {}
//}