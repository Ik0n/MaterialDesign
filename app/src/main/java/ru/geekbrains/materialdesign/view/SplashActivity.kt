package ru.geekbrains.materialdesign.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.materialdesign.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyBlueTheme)
        setContentView(R.layout.activity_splash)

        findViewById<ImageView>(R.id.splash_screen_image)
            .animate()
            .rotation(1080f)
            .setDuration(4000L)
            .start()

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 4000L)
    }

}