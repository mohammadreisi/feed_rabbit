package com.example.feedtherabbit.presenter.views.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.feedtherabbit.business.model.Line

@Composable
fun LineView(modifier: Modifier = Modifier, line: Line, onLineClicked: (Int, Int) -> Unit) {
    Column(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onLineClicked(line.leftX, line.rightX)
        }
    ) {}
}
