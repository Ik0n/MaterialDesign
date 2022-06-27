package ru.geekbrains.materialdesign.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.geekbrains.materialdesign.BuildConfig
import ru.geekbrains.materialdesign.model.PictureOfTheDayRetrofitImpl
import ru.geekbrains.materialdesign.model.PictureOfTheDayServerResponseData
import ru.geekbrains.materialdesign.utills.DAY_BEFORE_YESTERDAY
import ru.geekbrains.materialdesign.utills.TODAY
import ru.geekbrains.materialdesign.utills.YESTERDAY

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {
    fun getLiveDataFromViewToObserve () = liveDataForViewToObserve
    fun sendServerRequest(day: String) {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        when(day) {
            TODAY -> retrofitImpl
                .getRetrofitImpl()
                .getPictureOfTheDay(BuildConfig.NASA_API_KEY)
                .enqueue(callback)
            YESTERDAY -> retrofitImpl
                .getRetrofitImpl()
                .getPictureOfTheYesterday(BuildConfig.NASA_API_KEY)
                .enqueue(callback)
            DAY_BEFORE_YESTERDAY -> retrofitImpl
                .getRetrofitImpl()
                .getPictureOfTheDayBeforeYesterday(BuildConfig.NASA_API_KEY)
                .enqueue(callback)
        }

    }

    val callback = object : Callback<PictureOfTheDayServerResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayServerResponseData>,
            response: Response<PictureOfTheDayServerResponseData>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveDataForViewToObserve.postValue(AppState.Success(it))
                }
            } else {
                liveDataForViewToObserve.postValue(AppState.Error(Throwable("Server is offline")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.postValue(AppState.Error(t))
        }
    }
}