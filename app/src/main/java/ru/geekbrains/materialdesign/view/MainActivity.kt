package ru.geekbrains.materialdesign.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.utills.Parameters
import ru.geekbrains.materialdesign.view.pictureoftheday.PictureOfTheDayFragment
import ru.geekbrains.materialdesign.view.settings.THEME

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Parameters.getInstance().theme = getPreferencesTheme()
        setTheme(Parameters.getInstance().theme)

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
}