package com.example.jumpeak.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.jumpeak.auth.view.LoginActivity
import com.example.jumpeak.R
import com.example.jumpeak.reg.RegEmailActivity

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()
        actionBar?.setBackgroundDrawable(ColorDrawable(R.color.purple))
        val btnLog=findViewById<Button>(R.id.btnLogin)
        val btnReg=findViewById<Button>(R.id.btnReg)
        btnLog.setOnClickListener{startActivity(Intent(this, LoginActivity::class.java))}
        try{
            btnReg.setOnClickListener{startActivity(Intent(this, RegEmailActivity::class.java))}
        }
        catch(e:Exception){
            Toast.makeText(this,e.message.toString(),Toast.LENGTH_SHORT).show()
        }

    }
}