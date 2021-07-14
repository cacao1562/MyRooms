package com.example.myrooms.di

import com.example.myrooms.api.ApiService
import com.example.myrooms.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        service: ApiService,
    ): MainRepository {
        return MainRepository(service)
    }

}