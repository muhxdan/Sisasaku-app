package com.salt.apps.core.di

import com.salt.apps.core.domain.repository.IRepository
import com.salt.apps.core.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideUseCase(authRepository: IRepository): UseCase = UseCase(authRepository)
}
