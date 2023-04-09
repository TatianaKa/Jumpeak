package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jumpeak.R

class RegMailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_mail)
        supportActionBar?.hide();
        val btnNext=findViewById<Button>(R.id.btnRegNext)
        btnNext.setOnClickListener{startActivity(Intent(this,RegFirstPassActivity::class.java))}
    }
}