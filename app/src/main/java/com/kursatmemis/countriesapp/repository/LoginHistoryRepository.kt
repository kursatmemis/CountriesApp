package com.kursatmemis.countriesapp.repository

import androidx.lifecycle.MutableLiveData
import com.kursatmemis.countriesapp.util.SharedPrefManager
import com.kursatmemis.countriesapp.util.millisToSecond
import javax.inject.Inject

class LoginHistoryRepository @Inject constructor(private val sharedPrefManager: SharedPrefManager) {

    val lastOpenTimeLiveData = MutableLiveData<Long>()
    val currentTimeLiveData = MutableLiveData<Long>()

    init {
        lastOpenTimeLiveData.value = sharedPrefManager.getData("lastOpenTime", 0)
        currentTimeLiveData.value = System.currentTimeMillis()

        if (isFirstOpen()) {
            lastOpenTimeLiveData.value = currentTimeLiveData.value
            sharedPrefManager.saveData("lastOpenTime", lastOpenTimeLiveData.value)
        }
    }

    fun isTenSecondsPassedLastOpen() : Boolean {
        val timeInMillis = (currentTimeLiveData.value!! - lastOpenTimeLiveData.value!!)
        val timeInSecond = millisToSecond(timeInMillis)
        return timeInSecond >= 10
    }

    fun isFirstOpen() : Boolean {
        return lastOpenTimeLiveData.value == 0.toLong()
    }

    fun updateLastOpen() {
        sharedPrefManager.saveData("lastOpenTime", currentTimeLiveData.value)
    }

}