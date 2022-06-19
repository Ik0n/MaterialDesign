package ru.geekbrains.materialdesign.model

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.materialdesign.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

interface PictureOfTheDayAPI {
    @GET("/planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String) : Call<PictureOfTheDayServerResponseData>


    @GET("planetary/apod")
    fun getPictureOfTheYesterday(
        @Query("api_key") apiKey: String,
        @Query("date") date: String = SimpleDateFormat("yyyy-MM-dd")
            .format(Date(System.currentTimeMillis() - 86400000))
    ) : Call<PictureOfTheDayServerResponseData>

    @GET("planetary/apod")
    fun getPictureOfTheDayBeforeYesterday(
        @Query("api_key") apiKey: String,
        @Query("date") date: String = SimpleDateFormat("yyyy-MM-dd")
            .format(System.currentTimeMillis() - (86400000 * 2))
    ) : Call<PictureOfTheDayServerResponseData>

}