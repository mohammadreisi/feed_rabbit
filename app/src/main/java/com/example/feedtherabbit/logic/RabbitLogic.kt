package com.example.feedtherabbit.logic

import com.example.feedtherabbit.business.model.Rabbit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RabbitLogic(firstRabbitPosition: Int) {
    private val _rabbitPosition = MutableStateFlow(Rabbit().apply { setLeftX(firstRabbitPosition) })
    val rabbitPosition: StateFlow<Rabbit> = _rabbitPosition

    fun setRabbitPosition(rabbit: Rabbit) {
        _rabbitPosition.value = rabbit
    }
}