package com.example.feedtherabbit.presenter.views.element

import androidx.compose.foundation.background
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.feedtherabbit.R


@Composable
fun HomeRabbitView(modifier: Modifier = Modifier, pagerState: PagerState) {

    HorizontalPager(verticalAlignment = Alignment.CenterVertically, state = pagerState) { currentPageNumber ->

        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                when (currentPageNumber) {
                    0 -> {
                        R.raw.rabbit2
                    }
                    else -> {
                        R.raw.rabbit
                    }
                }
            )
        )

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )


        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = modifier.background(Color.Transparent)
        )
    }

}
