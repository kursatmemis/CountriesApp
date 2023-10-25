package com.kursatmemis.countriesapp.di.modules

import android.content.Context
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.view.adapter.AdapterDataLoader
import com.kursatmemis.countriesapp.view.adapter.CountryAdapterDataLoader
import com.kursatmemis.countriesapp.view.adapter.CustomAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @FragmentScoped
    @Provides
    fun provideCustomAdapter(
        @ApplicationContext context: Context,
        @CountryDataList dataList: ArrayList<DataModel>,
        @AdapterDataLoaderForCountry adapterDataLoader: AdapterDataLoader
    ): CustomAdapter {
        return CustomAdapter(context, dataList, adapterDataLoader)
    }

    @FragmentScoped
    @Provides
    @AdapterDataLoaderForCountry
    fun provideAdapterDataLoaderForCountry(): AdapterDataLoader {
        return CountryAdapterDataLoader()
    }

    @FragmentScoped
    @Provides
    @CountryDataList
    fun provideDataModelListForCountry(): ArrayList<DataModel> {
        return arrayListOf<Country>() as ArrayList<DataModel>
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountryDataList

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AdapterDataLoaderForCountry