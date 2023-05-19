package com.example.jumpeak.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.activity.WelcomeActivity
import com.example.jumpeak.R
import com.example.jumpeak.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide();
        Init()
    }
    private fun Init(){

        auth= FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener{onLogin()}
        binding.tvBackLogin.setOnClickListener{startActivity(Intent(this,WelcomeActivity::class.java))}

        binding.etPass.setOnFocusChangeListener{view, b -> binding.etPass.setBackgroundResource(R.drawable.shape_purple)}
        binding.etEmail.setOnFocusChangeListener{view, b -> binding.etEmail.setBackgroundResource(R.drawable.shape_purple)}
        //переделать цвет указателя?
        //переделать перелистывания окон
    }
    fun onLogin(){
        val auth= FirebaseAuth.getInstance()
        val email=binding.etEmail.text.toString()
        val pass=binding.etPass.text.toString()
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass) ){
            binding.etEmail.setBackgroundResource(R.drawable.shape_red)
            binding.etPass.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this, "Empty value",
                Toast.LENGTH_SHORT).show()
        }
        else if(TextUtils.isEmpty(email)) {
            binding.etEmail.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this, "Empty value email",
                Toast.LENGTH_SHORT).show()
        }
        else if(TextUtils.isEmpty(pass)){
            binding.etPass.setBackgroundResource(R.drawable.shape_red)
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
                    binding.etPass.setBackgroundResource(R.drawable.shape_red)
                    binding.etEmail.setBackgroundResource(R.drawable.shape_red)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}