package com.example.savethebird.logic.business_logic

import com.example.feedtherabbit.business.model.Rabbit
import com.example.feedtherabbit.logic.service.CarrotService
import com.example.feedtherabbit.logic.service.RabbitService
import com.example.savethebird.business.repository.AppRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val carrotService: CarrotService,
    private val rabbitService: RabbitService
) : AppRepository {

    override suspend fun setRabbitPosition(rabbit: Rabbit) {
        rabbitService.setRabbitPosition(rabbit)
    }

    override suspend fun listenRabbitPosition() = flow {
        rabbitService.rabbitPosition.collect { rabbit ->
            emit(rabbit)
        }
    }

    override suspend fun listenCarrotPosition() = flow {
        carrotService.carrotPosition.collect { carrot ->
            emit(carrot)
        }
    }

    override suspend fun startCarrotFalling(screenHeight: Int) {
        carrotService.startCarrotFalling(screenHeight)
    }

    override suspend fun stopCarrotFalling() {
        carrotService.stopCarrotFalling()
    }
}