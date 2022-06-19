package ru.geekbrains.materialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.view.pictureoftheday.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }
}