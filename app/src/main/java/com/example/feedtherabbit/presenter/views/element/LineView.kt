package com.example.feedtherabbit.presenter.views.element

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feedtherabbit.business.model.Line

@Composable
fun LineView(modifier: Modifier = Modifier, line: Line, onLineClicked: (Int, Int) -> Unit) {
    Column(
        modifier = modifier.clickable {
            onLineClicked(line.leftX, line.rightX)
        }
    ) {}
}
