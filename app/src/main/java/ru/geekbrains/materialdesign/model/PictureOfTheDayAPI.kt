package ru.geekbrains.materialdesign.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.materialdesign.utills.API_KEY
import ru.geekbrains.materialdesign.utills.DATE
import ru.geekbrains.materialdesign.utills.PLANETARY_APOD
import java.text.SimpleDateFormat
import java.util.*

interface PictureOfTheDayAPI {
    @GET(PLANETARY_APOD)
    fun getPictureOfTheDay(@Query(API_KEY) apiKey:String) : Call<PictureOfTheDayServerResponseData>


    @GET(PLANETARY_APOD)
    fun getPictureOfTheYesterday(
        @Query(API_KEY) apiKey: String,
        @Query(DATE) date: String = SimpleDateFormat("yyyy-MM-dd")
            .format(Date(System.currentTimeMillis() - 86400000))
    ) : Call<PictureOfTheDayServerResponseData>

    @GET(PLANETARY_APOD)
    fun getPictureOfTheDayBeforeYesterday(
        @Query(API_KEY) apiKey: String,
        @Query(DATE) date: String = SimpleDateFormat("yyyy-MM-dd")
            .format(System.currentTimeMillis() - (86400000 * 2))
    ) : Call<PictureOfTheDayServerResponseData>

}