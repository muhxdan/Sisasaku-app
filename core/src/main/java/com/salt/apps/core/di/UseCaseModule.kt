package com.salt.apps.core.di

import com.salt.apps.core.domain.repository.IAuthRepository
import com.salt.apps.core.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideUseCase(authRepository: IAuthRepository): AuthUseCase = AuthUseCase(authRepository)
}
