package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jumpeak.R
import com.example.jumpeak.databinding.ActivityRegSecondPassBinding
import com.google.firebase.auth.FirebaseAuth

class RegSecondPassActivity : AppCompatActivity() {
    private lateinit  var etPass: EditText
    private lateinit var btnCreate: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_second_pass)
        supportActionBar?.hide();
        Init()
    }
    private fun Init(){
        etPass=findViewById(R.id.etSecondPass)
        btnCreate=findViewById(R.id.btnCreateAccount)
        auth= FirebaseAuth.getInstance()
        etPass.setOnFocusChangeListener{view, b -> etPass.setBackgroundResource(R.drawable.shape_purple)}
       // btnCreate.setOnClickListener { createNewAccount() }
        //переделать цвет указателя?
    }
   /* fun createNewAccount() {
            auth.createUserWithEmailAndPassword(etEmail.text.toString(), etPass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Сработало",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
    }*/
}