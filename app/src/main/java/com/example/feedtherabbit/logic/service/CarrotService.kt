package com.example.feedtherabbit.logic.service

import com.example.feedtherabbit.business.model.Carrot
import com.example.feedtherabbit.logic.CARROT_HEIGHT
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
            Carrot(YTop = (CARROT_HEIGHT * -2), YBottom = CARROT_HEIGHT * -1, linePosition = getRandomLine())
        delay(2000)
        while (isCarrotFalling) {
            _carrotPosition.emit(carrot.copy())
            carrot.apply {
                YTop += 5
                YBottom += 5
            }
            if (carrot.YTop > screenHeight) {
                carrot.apply {
                    YTop = (CARROT_HEIGHT * -2)
                    YBottom = CARROT_HEIGHT * -1
                    linePosition = getRandomLine()
                }
            }
            delay(50)
        }
    }

    suspend fun stopCarrotFalling() {
        isCarrotFalling = false
    }

}