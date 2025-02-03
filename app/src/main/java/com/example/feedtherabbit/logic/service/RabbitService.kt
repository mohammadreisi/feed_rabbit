package com.example.feedtherabbit.logic.service

import com.example.feedtherabbit.business.model.Rabbit
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class RabbitService() {
    private val _rabbitPosition = MutableSharedFlow<Rabbit>()
    val rabbitPosition: SharedFlow<Rabbit> = _rabbitPosition

    suspend fun setRabbitPosition(rabbit: Rabbit) {
        _rabbitPosition.emit(rabbit)
    }
}