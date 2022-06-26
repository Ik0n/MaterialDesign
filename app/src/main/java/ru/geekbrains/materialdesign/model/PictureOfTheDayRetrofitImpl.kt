package ru.geekbrains.materialdesign.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.materialdesign.utills.BASE_URL

class PictureOfTheDayRetrofitImpl {

    fun getRetrofitImpl() : PictureOfTheDayAPI {

        val podRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }

}