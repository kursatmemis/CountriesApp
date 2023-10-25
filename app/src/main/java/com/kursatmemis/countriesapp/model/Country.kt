package com.kursatmemis.countriesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country(
    val name: String?,
    val capital: String?,
    val region: String?,
    val currency: String?,
    val flag: String?,
    val language: String?
) : DataModel {

    @PrimaryKey(autoGenerate = true)
    private var countryId: Int? = null

    fun setCountryId(id: Int) {
        countryId = id
    }

    fun getCountryId(): Int? {
        return countryId
    }

}
