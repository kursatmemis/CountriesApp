package com.kursatmemis.countriesapp.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kursatmemis.countriesapp.dao.CountryDao
import com.kursatmemis.countriesapp.model.Country

@Database(entities = [Country::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

}