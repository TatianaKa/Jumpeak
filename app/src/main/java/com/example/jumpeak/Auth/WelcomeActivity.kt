package com.example.jumpeak.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.jumpeak.MainActivity
import com.example.jumpeak.R

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()
        actionBar?.setBackgroundDrawable(ColorDrawable(R.color.purple))
        val btnLog=findViewById<Button>(R.id.btnLogin)
        val btnReg=findViewById<Button>(R.id.btnReg)
        btnLog.setOnClickListener{startActivity(Intent(this,LoginActivity::class.java))}

        btnReg.setOnClickListener{startActivity(Intent(this,RegMailActivity::class.java))}
    }
}