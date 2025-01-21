package com.example.feedtherabbit.presenter.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedtherabbit.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {

    private var mediaPlayer: MediaPlayer? = null

    private val _musicPlayingState = MutableStateFlow(true)
    val musicPlayingState: StateFlow<Boolean> = _musicPlayingState

    init {
        playMusic()
    }

    fun playMusic() {
        viewModelScope.launch {
            mediaPlayer = MediaPlayer.create(context, R.raw.koodak_bikalam)
            mediaPlayer?.apply {
                isLooping = true
                start()
            }
            _musicPlayingState.emit(true)
        }
    }

    fun stopMusic() {
        viewModelScope.launch {
            if (mediaPlayer != null) {
                mediaPlayer?.apply {
                    stop()
                    release()
                }
            }
            _musicPlayingState.emit(false)
        }
    }

}