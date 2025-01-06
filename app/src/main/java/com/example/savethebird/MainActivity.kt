package com.example.savethebird

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.savethebird.ui.theme.SaveTheBirdTheme
import com.example.savethebird.ui.views.viewgroup.MainActivityView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SaveTheBirdTheme {
                MainActivityView()
            }
        }
    }
}

