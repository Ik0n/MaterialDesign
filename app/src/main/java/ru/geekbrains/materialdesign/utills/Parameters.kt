package ru.geekbrains.materialdesign.utills

import androidx.appcompat.app.AppCompatDelegate
import ru.geekbrains.materialdesign.R

class Parameters {

    var resetFragment: Boolean = false
    var theme: Int = R.style.MySplashTheme
    var mode: Int = AppCompatDelegate.MODE_NIGHT_NO

    companion object {
        @Volatile
        private var INSTANCE: Parameters? = null
        fun getInstance(): Parameters {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Parameters()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}