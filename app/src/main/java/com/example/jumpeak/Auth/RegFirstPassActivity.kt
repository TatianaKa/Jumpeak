package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.ClassHelper.User
import com.example.jumpeak.R

class RegFirstPassActivity : AppCompatActivity() {
    private lateinit var etPass: EditText
    private lateinit var tvEnterPass: TextView
    private lateinit var btnFirstPass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_first_pass)
        supportActionBar?.hide();
        btnFirstPass=findViewById(R.id.btnFirstPass)
        etPass=findViewById(R.id.etFirstPass)
        tvEnterPass=findViewById(R.id.tvEnterPass)
        etPass.setOnFocusChangeListener{view, b -> etPass.setBackgroundResource(R.drawable.shape_purple)}
        btnFirstPass.setOnClickListener{startActivity(Intent(this,RegSecondPassActivity::class.java))}

        //val str = intent.getStringExtra("email")
        val user:User= User()
        val str = user.login

        tvEnterPass.setText(str)
    }
}