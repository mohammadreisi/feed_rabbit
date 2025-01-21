package com.example.feedtherabbit.presenter.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.savethebird.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    init {
        playMusic()
    }

    private fun playMusic() {
        if (mediaPlayer == null || mediaPlayer?.isPlaying == false) {
            mediaPlayer = MediaPlayer.create(context, R.raw.koodak_bikalam)
            mediaPlayer?.apply {
                isLooping = true
                prepare()
                start()
            }
        }
    }

    private fun stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer?.apply {
                stop()
                release()
            }
        }
    }

}