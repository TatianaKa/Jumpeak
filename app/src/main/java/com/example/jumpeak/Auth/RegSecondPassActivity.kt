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
    private lateinit var etEmail: EditText
    private lateinit  var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_second_pass)
        supportActionBar?.hide();
        val btnCreate=findViewById<Button>(R.id.btnCreateAccount)
        btnCreate.setOnClickListener{createNewAccount()}
        etPassword=findViewById(R.id.etSecondPass)

    }
    fun createNewAccount() {
        // if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)
        //         && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        //if(etPassword.text.toString)
        var mAuth= FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Сработало",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}