package com.example.jumpeak.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.Activity.WelcomeActivity
import com.example.jumpeak.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText

    private lateinit var btnLogin: Button

    private lateinit var tvReg: TextView
    private lateinit var tvBackLogin: TextView

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide();
        Init()
    }
    private fun Init(){
        etEmail=findViewById(R.id.etEmail)
        etPass=findViewById(R.id.etPass)

        btnLogin=findViewById(R.id.btnLogin)

        tvReg=findViewById(R.id.tvErrorLog)//клик
        tvBackLogin=findViewById(R.id.tvBackLogin)

        auth= FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{onLogin()}

        tvBackLogin.setOnClickListener{startActivity(Intent(this,WelcomeActivity::class.java))}

        etPass.setOnFocusChangeListener{view, b -> etPass.setBackgroundResource(R.drawable.shape_purple)}
        etEmail.setOnFocusChangeListener{view, b -> etEmail.setBackgroundResource(R.drawable.shape_purple)}
        //переделать цвет указателя?
        //переделать перелистывания окон
    }
    fun onLogin(){
        val auth= FirebaseAuth.getInstance()
        val email=etEmail.text.toString()
        val pass=etPass.text.toString()
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass) ){
            etEmail.setBackgroundResource(R.drawable.shape_red)
            etPass.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this, "Empty value",
                Toast.LENGTH_SHORT).show()
        }
        else if(TextUtils.isEmpty(email)) {
            etEmail.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this, "Empty value email",
                Toast.LENGTH_SHORT).show()
        }
        else if(TextUtils.isEmpty(pass)){
            etPass.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this, "Empty value password",
                Toast.LENGTH_SHORT).show()
        }
        else{
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
                    task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Authentication", Toast.LENGTH_SHORT).show()
                }
                else{
                    etPass.setBackgroundResource(R.drawable.shape_red)
                    etEmail.setBackgroundResource(R.drawable.shape_red)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}