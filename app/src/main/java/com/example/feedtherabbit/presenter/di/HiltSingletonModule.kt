package com.example.feedtherabbit.presenter.di

import com.example.feedtherabbit.logic.service.CarrotService
import com.example.feedtherabbit.logic.service.RabbitService
import com.example.savethebird.business.repository.AppRepository
import com.example.savethebird.logic.business_logic.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltSingletonModule {

    @Provides
    @Singleton
    fun provideRabbitService(): RabbitService {
        return RabbitService()
    }

    @Provides
    @Singleton
    fun provideCarrotService(): CarrotService {
        return CarrotService()
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        carrotService: CarrotService,
        rabbitService: RabbitService
    ): AppRepository {
        return AppRepositoryImpl(carrotService = carrotService, rabbitService = rabbitService)
    }
}