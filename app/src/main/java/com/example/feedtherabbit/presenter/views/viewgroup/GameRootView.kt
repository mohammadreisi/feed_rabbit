package com.example.feedtherabbit.presenter.views.viewgroup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.feedtherabbit.R
import com.example.feedtherabbit.business.model.Line
import com.example.feedtherabbit.business.model.Rabbit
import com.example.feedtherabbit.logic.CarrotLogic
import com.example.feedtherabbit.logic.RABBIT_WIDTH
import com.example.feedtherabbit.logic.RabbitLogic
import com.example.feedtherabbit.presenter.views.element.LineView
import com.example.feedtherabbit.presenter.views.element.RabbitView


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GameRootView(
    modifier: Modifier = Modifier,
    shareElementName: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onFinishClicked: () -> Unit
) {

    with(sharedTransitionScope) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp
        val screenWidth = configuration.screenWidthDp

        val carrotLogic = remember { CarrotLogic() }
        val newCarrot = carrotLogic.createCarrot.collectAsState()

        val rabbitObject =
            remember {
                RabbitLogic(
                    firstRabbitPosition = getLineCenter(
                        screenWidth / 4,
                        screenWidth / 2
                    )
                )
            }
        val rabbit = rabbitObject.rabbitPosition.collectAsState()

        val rabbitX by animateDpAsState(
            targetValue = rabbit.value.getLeftX().dp,
            label = "offset"
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.paint(
                    painterResource(R.drawable.ms2),
                    contentScale = ContentScale.FillBounds
                )
            ) {
                LineView(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenWidth / 4).dp)
                        .background(Color.Transparent),
                    line = Line(leftX = 0, rightX = screenWidth / 4)

                ) { left, right ->
                    rabbitObject.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getLineCenter(
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
                    rabbitObject.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getLineCenter(
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
                    rabbitObject.setRabbitPosition(Rabbit().apply {
                        setLeftX(
                            getLineCenter(
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
                    .offset {
                        IntOffset(
                            x = rabbitX
                                .toPx()
                                .toInt(),
                            y = (screenHeight * 0.70).dp
                                .toPx()
                                .toInt()
                        )
                    }
                    .height(RABBIT_WIDTH.dp)
                    .width(RABBIT_WIDTH.dp)
                    .sharedElement(
                        state = rememberSharedContentState(shareElementName),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
            )

        }
    }
}

fun getLineCenter(left: Int, right: Int): Int {
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