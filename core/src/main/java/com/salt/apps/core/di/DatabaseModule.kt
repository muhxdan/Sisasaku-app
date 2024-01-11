package com.salt.apps.core.di

import android.content.Context
import androidx.room.Room
import com.salt.apps.core.data.source.local.room.SsDatabase
import com.salt.apps.core.data.source.local.room.dao.UserDao
import com.salt.apps.core.utilities.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SsDatabase::class.java,
            name = DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: SsDatabase): UserDao = database.userDao()
}