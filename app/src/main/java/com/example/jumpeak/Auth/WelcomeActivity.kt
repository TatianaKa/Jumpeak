package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jumpeak.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide();
        val btnLog=findViewById<Button>(R.id.btnLogin)
        val btnReg=findViewById<Button>(R.id.btnReg)
        btnLog.setOnClickListener{startActivity(Intent(this,LoginActivity::class.java))}
        btnReg.setOnClickListener{startActivity(Intent(this,RegMailActivity::class.java))}
    }
}