package ru.geekbrains.materialdesign.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.utills.MODE
import ru.geekbrains.materialdesign.utills.Parameters
import ru.geekbrains.materialdesign.utills.THEME
import ru.geekbrains.materialdesign.view.pictureoftheday.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Parameters.getInstance().apply {
            theme = getPreferencesTheme()
            mode = getPreferencesMode()
        }

        setTheme(Parameters.getInstance().theme)
        AppCompatDelegate.setDefaultNightMode(Parameters.getInstance().mode)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }

    private fun getPreferencesTheme(): Int {
        return getPreferences(Context.MODE_PRIVATE).getInt(THEME, R.style.MyBlueTheme)
    }

    private fun getPreferencesMode(): Int {
        return getPreferences(Context.MODE_PRIVATE).getInt(MODE, AppCompatDelegate.MODE_NIGHT_NO)
    }
}