package com.kursatmemis.countriesapp.di.modules

import androidx.lifecycle.MutableLiveData
import com.kursatmemis.countriesapp.dao.CountryDao
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.repository.LoginHistoryRepository
import com.kursatmemis.countriesapp.repository.RetrofitRepository
import com.kursatmemis.countriesapp.repository.RoomRepository
import com.kursatmemis.countriesapp.service.CountryService
import com.kursatmemis.countriesapp.util.ErrorHandler
import com.kursatmemis.countriesapp.util.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideRoomRepository(
        countryDao: CountryDao,
        dataModelListLiveData: MutableLiveData<List<DataModel>>
    ): RoomRepository {
        return RoomRepository(countryDao, dataModelListLiveData)
    }

    @ViewModelScoped
    @Provides
    fun provideRetrofitRepository(
        countryService: CountryService,
        dataModelListLiveData: MutableLiveData<List<DataModel>>,
        errorHandler: ErrorHandler
    ): RetrofitRepository {
        return RetrofitRepository(countryService, dataModelListLiveData, errorHandler)
    }

    @ViewModelScoped
    @Provides
    fun provideErrorHandler() : ErrorHandler {
        return ErrorHandler()
    }

    @ViewModelScoped
    @Provides
    fun provideLoginHistoryRepository(sharedPrefManager: SharedPrefManager): LoginHistoryRepository {
        return LoginHistoryRepository(sharedPrefManager)
    }

    @ViewModelScoped
    @Provides
    fun provideDataModelListLiveData(): MutableLiveData<List<DataModel>> {
        val dataList = arrayListOf<DataModel>()
        return MutableLiveData(dataList)
    }

}