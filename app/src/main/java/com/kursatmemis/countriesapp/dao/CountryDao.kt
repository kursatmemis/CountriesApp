package com.kursatmemis.countriesapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel

@Dao
interface CountryDao {

    @Insert
    suspend fun insertCountry(country: Country)

    @Insert
    suspend fun insertMultiCountry(countryList: List<Country>)

    @Update
    suspend fun updateCountryList(countryList: List<Country>)

    @Query("Select * from country_table")
    suspend fun getCountryList() : List<Country>

    @Query("Delete from country_table")
    suspend fun deleteAllData()

}

