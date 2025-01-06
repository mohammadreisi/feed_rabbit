package com.example.savethebird.ui.views.viewgroup

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.savethebird.R
import com.example.savethebird.logics.RabbitLogic
import com.example.savethebird.logics.Rabbit_WIDTH
import com.example.savethebird.models.Line
import com.example.savethebird.models.Rabbit
import com.example.savethebird.ui.views.element.LineView
import com.example.savethebird.ui.views.element.RabbitView


@Composable
fun GameRootView(modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

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
                .height(Rabbit_WIDTH.dp)
                .width(Rabbit_WIDTH.dp)
        )

    }
}

fun getLineCenter(left: Int, right: Int): Int {
    return ((left + right) / 2) - ((Rabbit_WIDTH / 2))
}

@Preview
@Composable
private fun Preview() {
    GameRootView(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
}