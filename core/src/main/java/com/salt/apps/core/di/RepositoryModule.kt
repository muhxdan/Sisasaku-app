package com.salt.apps.core.di

import com.salt.apps.core.data.Repository
import com.salt.apps.core.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: Repository): IRepository
}