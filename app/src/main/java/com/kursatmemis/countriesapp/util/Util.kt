package com.kursatmemis.countriesapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kursatmemis.countriesapp.R
import com.kursatmemis.countriesapp.databinding.FragmentDetailBinding
import com.kursatmemis.countriesapp.model.Country
import java.io.Serializable

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable) // Resim yüklenene kadar resmin yerinde görüntülenmesini istediğimiz drawable belirlendi.
        .error(R.mipmap.ic_launcher_round) // Eğerki resim yüklenirken hata olursa buradaki mipmap o resmin yerine gözükecek.

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                // Resim yüklemesi başarısız olduğunda burası çalışır.
                // Hata işleme veya bildirim gösterme gibi işlemleri burada ekleyebilirsiniz.
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                // Resim yüklendikten sonra burası çalışır.
                // İlerleme çubuğunu durdurabiliriz.
                progressDrawable.stop()
                return false
            }

        })
        .into(this)

    progressDrawable.start()

}

fun placeHolderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        // Resim yüklenene kadar gösterilecek progressBar'ın kalınlık ve yarıçapı ayarlandı.
        strokeWidth = 10f
        centerRadius = 40f
    }
}

fun millisToSecond(millis: Long) : Long {
    // 1 saniye = 1.000 millis
    return millis / 1000L
}

fun showToastMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

class ErrorHandler {
    private var isError = MutableLiveData<Boolean>()
    private var errorMessage: String? = null

    fun setIsError(value: Boolean) {
        isError.value = value
    }

    fun getIsError(): MutableLiveData<Boolean> {
        return isError
    }

    fun setErrorMessage(errorMessage: String?) {
        if (errorMessage == null) {
            this.errorMessage = "Error! Please try again later."
        } else {
            this.errorMessage = errorMessage
        }
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

}

class SharedPrefManager(val context: Context) {

    private val SHARED_PREF_NAME: String = "MySharedPref"
    private val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val edit = sharedPref.edit()

    fun <T> saveData(key: String, value: T) {
        when (value) {
            is String -> {
                edit.putString(key, value)
            }

            is Int -> {
                edit.putInt(key, value)
            }

            is Long -> {
                edit.putLong(key, value)
            }

            is Boolean -> {
                edit.putBoolean(key, value)
            }

            is Float -> {
                edit.putFloat(key, value)
            }

        }
        edit.apply()
    }

    fun <T> getData(key: String, defaultValue: T): T {
        return when (defaultValue) {

            is String -> {
                sharedPref.getString(key, defaultValue) as T
            }

            is Int -> {
                sharedPref.getInt(key, defaultValue) as T
            }

            is Long -> {
                sharedPref.getLong(key, defaultValue) as T
            }

            is Boolean -> {
                sharedPref.getBoolean(key, defaultValue) as T
            }

            is Float -> {
                sharedPref.getFloat(key, defaultValue) as T
            }

            else -> {
                defaultValue
            }

        }
    }

}