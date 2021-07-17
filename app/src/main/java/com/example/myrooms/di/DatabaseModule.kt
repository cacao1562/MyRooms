package com.example.myrooms.di

import android.app.Application
import androidx.room.Room
import com.example.myrooms.db.RoomDao
import com.example.myrooms.db.RoomInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RoomInfoDatabase {
        return Room
            .databaseBuilder(app, RoomInfoDatabase::class.java, "roomInfo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRoomDao(roomInfoDatabase: RoomInfoDatabase): RoomDao{
        return roomInfoDatabase.roomDao()
    }
}