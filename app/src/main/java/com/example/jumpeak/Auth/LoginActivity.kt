package com.example.jumpeak.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jumpeak.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide();
        val btnLogin=findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{onLogin()}
    }
    fun onLogin(){
        val etEmail=findViewById<EditText>(R.id.etEmail)
        val etPass=findViewById<EditText>(R.id.etPass)
        val auth= FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(etEmail.text.toString(),etPass.text.toString()).addOnCompleteListener(this){
                task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Authentication", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}