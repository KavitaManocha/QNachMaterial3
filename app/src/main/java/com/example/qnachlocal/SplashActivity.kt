package com.example.qnachlocal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.qnachlocal.data.local.SessionManager
import com.google.android.material.textview.MaterialTextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash)
        val appVersion = findViewById<MaterialTextView>(R.id.appVersion)
        appVersion.setText(BuildConfig.VERSION_NAME)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val sessionManager= SessionManager(this)
            if(sessionManager.getUserDetail()!=null){
                val i = Intent(this@SplashActivity, DashBoardActivity::class.java)
                startActivity(i)
                finish()
            }
            else{
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }

        }, SPLASH_DELAY)
    }
}