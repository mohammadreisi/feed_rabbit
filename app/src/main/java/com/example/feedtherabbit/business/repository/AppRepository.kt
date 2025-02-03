package com.example.savethebird.business.repository

import com.example.feedtherabbit.business.model.Carrot
import com.example.feedtherabbit.business.model.Rabbit
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun setRabbitPosition(rabbit: Rabbit)
    suspend fun listenRabbitPosition(): Flow<Rabbit>
    suspend fun startCarrotFalling(screenHeight: Int)
    suspend fun stopCarrotFalling()
    suspend fun listenCarrotPosition(): Flow<Carrot>
}