package com.claro.tv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.claro.tv.R
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }

    /**
     * Método que crea un delay de 5 segundos
     */
    private fun scheduleSplashScreen() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(5))
            withContext(Dispatchers.Main) {
                startActivity(MainActivity.newIntent(applicationContext))
            }
        }
    }
}
