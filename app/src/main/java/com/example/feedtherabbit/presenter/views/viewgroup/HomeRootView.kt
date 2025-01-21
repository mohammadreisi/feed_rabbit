package com.example.feedtherabbit.presenter.views.viewgroup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedtherabbit.presenter.view_model.HomeViewModel
import com.example.feedtherabbit.presenter.views.element.RabbitView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeRootView(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onStartClicked: (String) -> Unit
) {
    with(sharedTransitionScope) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.fillMaxHeight(0.05f))
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
                text = "خرگوش",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
                text = "شکمو",
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.fillMaxHeight(0.1f))
            Button(
                modifier = Modifier,
                onClick = {onStartClicked("rabbit")},
                shape = MaterialTheme.shapes.small,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                border = BorderStroke(5.dp, color = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "شروع",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(Modifier.fillMaxHeight(0.15f))
            RabbitView(
                modifier = Modifier
                    .height(320.dp)
                    .width(320.dp)
                    .sharedElement(
                        state = rememberSharedContentState("rabbit"),
                        animatedVisibilityScope = animatedContentScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
            )
        }
    }
}

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
//            null
//        ) {}
//    }
//}