package com.kursatmemis.countriesapp.di.modules

import android.content.Context
import androidx.room.Room
import com.kursatmemis.countriesapp.config.AppDataBase
import com.kursatmemis.countriesapp.config.CountryApiClient
import com.kursatmemis.countriesapp.dao.CountryDao
import com.kursatmemis.countriesapp.service.CountryService
import com.kursatmemis.countriesapp.util.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCountryDao(@ApplicationContext context: Context): CountryDao {
        return Room.databaseBuilder(context, AppDataBase::class.java, "AppDataBase")
            .build().countryDao()
    }

    @Singleton
    @Provides
    fun provideSharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Singleton
    @Provides
    fun provideCountryService(): CountryService {
        return CountryApiClient.getClient().create(CountryService::class.java)
    }

}
