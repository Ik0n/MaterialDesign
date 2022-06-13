package ru.geekbrains.materialdesign.utills

import ru.geekbrains.materialdesign.R

class Parameters {

    var resetFragment: Boolean = false
    var theme: Int = R.style.MyBlueTheme

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