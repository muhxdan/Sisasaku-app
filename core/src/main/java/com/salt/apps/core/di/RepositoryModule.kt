package com.salt.apps.core.di

import com.salt.apps.core.data.AuthRepository
import com.salt.apps.core.domain.repository.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(authRepository: AuthRepository): IAuthRepository
}