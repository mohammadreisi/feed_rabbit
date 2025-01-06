package com.example.savethebird.ui.views.viewgroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.savethebird.ui.theme.SaveTheBirdTheme

@Composable
fun HomeRootView(modifier: Modifier = Modifier, onStartClicked: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.fillMaxHeight(0.15f))
        Text(modifier = Modifier, style = MaterialTheme.typography.titleLarge, text = "خرگوش")
        Text(modifier = Modifier, style = MaterialTheme.typography.titleLarge, text = "شکمو")
        Spacer(Modifier.fillMaxHeight(0.3f))
        Button(modifier = Modifier.height(120.dp).width(120.dp), onClick = onStartClicked, shape = MaterialTheme.shapes.small) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = "START",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RootViewPreview() {
    SaveTheBirdTheme {
        HomeRootView(
            Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {}
    }
}