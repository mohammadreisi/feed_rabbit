package com.example.feedtherabbit.logic.service

import com.example.feedtherabbit.business.model.Carrot
import com.example.feedtherabbit.logic.CARROT_HEIGHT
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class CarrotService {
    private val _carrotPosition = MutableSharedFlow<Carrot>()
    val carrotPosition: SharedFlow<Carrot> = _carrotPosition

    @Volatile
    private var isCarrotFalling = false

    private fun getRandomLine(): Int {
        return (0..2).random()
    }

    suspend fun startCarrotFalling(screenHeight: Int) {
        isCarrotFalling = true
        val carrot =
            Carrot(YTop = (CARROT_HEIGHT * -1), YBottom = 0, linePosition = getRandomLine())
        while (isCarrotFalling) {
            _carrotPosition.emit(carrot)
            carrot.apply {
                YTop += 10
                YBottom += 10
            }
            if (carrot.YTop > screenHeight) {
                carrot.apply {
                    YTop = (CARROT_HEIGHT * -1)
                    YBottom = 0
                    linePosition = getRandomLine()
                }
            }
            delay(3000)
        }
    }

    suspend fun stopCarrotFalling() {
        isCarrotFalling = false
    }

}