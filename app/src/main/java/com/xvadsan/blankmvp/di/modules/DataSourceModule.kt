package com.xvadsan.blankmvp.di.modules

import android.content.Context
import androidx.room.Room
import com.xvadsan.blankmvp.data.RoomStorageRepository
import com.xvadsan.blankmvp.data.database.AppDatabase
import com.xvadsan.blankmvp.domain.RoomStorageDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomStorageDataSource(
        database: AppDatabase
    ): RoomStorageDataSource = RoomStorageRepository(database)

    companion object {
        private const val DB_NAME = "UsersDB"
    }
}