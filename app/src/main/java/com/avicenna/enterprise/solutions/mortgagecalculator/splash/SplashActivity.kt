package com.avicenna.enterprise.solutions.mortgagecalculator.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.avicenna.enterprise.solutions.mortgagecalculator.R
import com.avicenna.enterprise.solutions.mortgagecalculator.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViews()
    }

    private fun setupViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeScreen()
        }, 3000)
    }

    private fun goToHomeScreen() {
        startActivity(MainActivity.getCallingIntent(this@SplashActivity))
    }
}