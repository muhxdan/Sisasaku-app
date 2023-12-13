package com.salt.apps.core.di

import android.content.Context
import androidx.room.Room
import com.salt.apps.core.data.source.local.room.SsDatabase
import com.salt.apps.core.utilities.Constants.DB_NAME
import com.salt.apps.core.utilities.Constants.ENCRYPTED_DB_PASSPHRASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

object DatabaseModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {

        @Singleton
        @Provides
        fun provideDatabase(
            @ApplicationContext context: Context
        ): SsDatabase {
            val passphrase: ByteArray =
                SQLiteDatabase.getBytes(ENCRYPTED_DB_PASSPHRASE.toCharArray())
            val factory = SupportFactory(passphrase)
            return Room.databaseBuilder(
                context = context,
                klass = SsDatabase::class.java,
                name = DB_NAME
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory).build()
        }
    }
}