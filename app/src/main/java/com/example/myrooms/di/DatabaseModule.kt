package com.example.myrooms.di

import android.content.Context
import androidx.room.Room
import com.example.myrooms.db.FavoriteDao
import com.example.myrooms.db.RoomDao
import com.example.myrooms.db.RoomInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): RoomInfoDatabase {
        return Room
            .databaseBuilder(app, RoomInfoDatabase::class.java, "roomInfo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(roomInfoDatabase: RoomInfoDatabase): RoomDao{
        return roomInfoDatabase.roomDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(roomInfoDatabase: RoomInfoDatabase): FavoriteDao{
        return roomInfoDatabase.favoriteDao()
    }

}