package com.kursatmemis.countriesapp.repository

import androidx.lifecycle.MutableLiveData
import com.kursatmemis.countriesapp.dao.CountryDao
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val countryDao: CountryDao,
    private val dataModelListLiveData: MutableLiveData<List<DataModel>>
) {

    suspend fun saveDataModelListToRoom(countryList: List<Country>) {
        countryDao.insertMultiCountry(countryList)
    }

    suspend fun getDataModelListFromRoom() {
        dataModelListLiveData.value  = countryDao.getCountryList()
    }

    suspend fun deleteAllDataFromRoom() {
        countryDao.deleteAllData()
    }

}