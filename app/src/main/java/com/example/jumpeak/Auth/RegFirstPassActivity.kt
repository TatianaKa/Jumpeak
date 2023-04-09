package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.jumpeak.R

class RegFirstPassActivity : AppCompatActivity() {
    public  var email:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_first_pass)
        supportActionBar?.hide();
        val btnFirstPass=findViewById<Button>(R.id.btnFirstPass)
        val etEmail=findViewById<EditText>(R.id.etRegEmail)
        email=etEmail.text.toString()
        btnFirstPass.setOnClickListener{startActivity(Intent(this,RegSecondPassActivity::class.java))}

    }
}