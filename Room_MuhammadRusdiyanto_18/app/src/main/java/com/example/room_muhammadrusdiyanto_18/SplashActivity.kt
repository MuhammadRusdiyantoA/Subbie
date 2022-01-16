package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val anim1 = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.splash_anim2)

        val icon = findViewById<ImageView>(R.id.splash_icon1)
        val text = findViewById<TextView>(R.id.splash_text1)

        icon.startAnimation(anim1)
        text.startAnimation(anim2)

        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}