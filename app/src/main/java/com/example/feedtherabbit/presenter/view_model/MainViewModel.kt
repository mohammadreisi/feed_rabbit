package com.example.feedtherabbit.presenter.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feedtherabbit.R
import com.example.feedtherabbit.business.model.Carrot
import com.example.feedtherabbit.business.model.Rabbit
import com.example.savethebird.business.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val appRepository: AppRepository
) : ViewModel() {

    private var carrotCoroutineJob: Job? = null

    private var mediaPlayer: MediaPlayer? = null

    private val _musicPlayingState = MutableStateFlow(true)
    val musicPlayingState: StateFlow<Boolean> = _musicPlayingState

    private val _rabbitPosition = MutableLiveData<Rabbit>()
    val rabbitPosition: LiveData<Rabbit> = _rabbitPosition

    private val _carrotPosition = MutableLiveData<Carrot>()
    val carrotPosition: LiveData<Carrot> = _carrotPosition

    init {
        playMusic()
        listenCarrotPosition()
        listenRabbitPosition()
    }

    fun setRabbitPosition(rabbit: Rabbit) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.setRabbitPosition(rabbit)
        }
    }

    private fun listenRabbitPosition()  {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.listenRabbitPosition().collect { rabbit ->
                _rabbitPosition.postValue(rabbit)
            }
        }
    }

    private fun listenCarrotPosition() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.listenCarrotPosition().collect { carrot ->
                _carrotPosition.postValue(carrot)
            }
        }
    }

    fun startCarrotFalling(screenHeight: Int) {
        carrotCoroutineJob?.cancel()
        carrotCoroutineJob = viewModelScope.launch(Dispatchers.IO) {
            appRepository.startCarrotFalling(screenHeight)
        }
    }

    fun stopCarrotFalling() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.stopCarrotFalling()
        }
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