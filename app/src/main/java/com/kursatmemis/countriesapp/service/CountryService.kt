package com.kursatmemis.countriesapp.service

import com.kursatmemis.countriesapp.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountry(): Call<List<Country>>

}