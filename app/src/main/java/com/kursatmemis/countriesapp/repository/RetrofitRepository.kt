package com.kursatmemis.countriesapp.repository

import androidx.lifecycle.MutableLiveData
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.service.CountryService
import com.kursatmemis.countriesapp.util.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val countryService: CountryService,
    private val countryListLiveData: MutableLiveData<List<DataModel>>,
    val errorHandler: ErrorHandler
)  {

    fun getCountry(callback: (List<Country>) -> Unit) {
        countryService.getCountry().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    val countries = response.body()
                    if (countries != null) {
                        countryListLiveData.value = countries!!
                        callback(countries)
                    } else {
                        errorHandler.setErrorMessage("Response is null.")
                        errorHandler.setIsError(true)
                    }
                } else {
                    errorHandler.setErrorMessage(response.message())
                    errorHandler.setIsError(true)
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                errorHandler.setErrorMessage(t.message)
                errorHandler.setIsError(true)
            }
        })
    }

}



