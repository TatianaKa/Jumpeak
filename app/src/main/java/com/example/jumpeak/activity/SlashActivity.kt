package com.example.jumpeak.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.jumpeak.R
import com.google.firebase.auth.FirebaseAuth

class SlashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash)
        supportActionBar?.hide();
      //  val user= FirebaseAuth.getInstance().currentUser
        Handler(Looper.getMainLooper()).postDelayed({ startActivity(Intent(this, WelcomeActivity::class.java))
        },2000)
    }
}