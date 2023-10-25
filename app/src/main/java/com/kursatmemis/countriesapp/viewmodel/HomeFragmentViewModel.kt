package com.kursatmemis.countriesapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.repository.LoginHistoryRepository
import com.kursatmemis.countriesapp.repository.RetrofitRepository
import com.kursatmemis.countriesapp.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val retrofitRepository: RetrofitRepository,
    private val loginHistoryRepository: LoginHistoryRepository,
    val dataModelListLiveData: MutableLiveData<List<DataModel>>
) : ViewModel() {

    val isDataLoaded = MutableLiveData(false)
    val isError = retrofitRepository.errorHandler.getIsError()
    var errorMessage = retrofitRepository.errorHandler.getErrorMessage()

    fun fetchData() {
        isDataLoaded.value = false
        if (loginHistoryRepository.isTenSecondsPassedLastOpen() || loginHistoryRepository.isFirstOpen()) {
            getCountryFromApi()
            loginHistoryRepository.updateLastOpen()
            Log.w(
                "mKm - control",
                "from api ${(loginHistoryRepository.currentTimeLiveData.value!! / 1000 - loginHistoryRepository.lastOpenTimeLiveData.value!! / 1000)}"
            )
        } else {
            this.getCountryFromDatabase()
            Log.w(
                "mKm - control",
                "from database ${(loginHistoryRepository.currentTimeLiveData.value!! / 1000 - loginHistoryRepository.lastOpenTimeLiveData.value!! / 1000)}"
            )
        }
    }

    fun swipeRefreshData() {
        this.getCountryFromDatabase()
        loginHistoryRepository.updateLastOpen()
    }

    private fun getCountryFromApi() {
        retrofitRepository.getCountry {
            saveCountryToDatabase(it)
            isDataLoaded.value = true
        }
    }

    private fun getCountryFromDatabase() {
        viewModelScope.launch {
            roomRepository.getDataModelListFromRoom()
            isDataLoaded.value = true
        }
    }

    private fun saveCountryToDatabase(countryList: List<Country>) {
        viewModelScope.launch {
            roomRepository.deleteAllDataFromRoom()
            roomRepository.saveDataModelListToRoom(countryList)
        }
    }

}