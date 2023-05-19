package com.example.jumpeak.reg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.R
import com.example.jumpeak.databinding.ActivityRegSecondPassBinding
import com.example.jumpeak.firstStep.LetsGoActivity
import com.google.firebase.auth.FirebaseAuth

class RegSecondPassActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegSecondPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegSecondPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide();
        Init()
    }
    private fun Init(){
        auth= FirebaseAuth.getInstance()

        binding.etSecondPass.setOnFocusChangeListener{view, b -> binding.etSecondPass.setBackgroundResource(R.drawable.shape_purple)}
        binding.btnCreateAccount.setOnClickListener { createNewAccount() }

        //переделать цвет указателя?
    }
    fun createNewAccount() {
        val pass=binding.etSecondPass.text.toString()
        if( TextUtils.isEmpty(pass)){
            binding.tvChangePass.setText("\uD83D\uDE14, пустое значение пароля")
            binding.tvChangePass.setVisibility(View.VISIBLE)
        }
        else if(pass.count()<6)  {
            binding.tvChangePass.setText("\uD83D\uDE14, количество символов в пароле должно быть не менее 6")
            binding.tvChangePass.setVisibility(View.VISIBLE)
        }
        else{
            val email = intent.getStringExtra("email").toString()
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Пользователь успешно добавлен",
                            Toast.LENGTH_SHORT).show()
                        binding.tvChangePass.setVisibility(View.INVISIBLE)
                        startActivity(Intent(this@RegSecondPassActivity, LetsGoActivity::class.java))

                    } else {
                        //Toast.makeText(this, "Пользователь не добавлен",
                        //    Toast.LENGTH_SHORT).show()
                        binding.tvChangePass.setText("Пользователь не добавлен")
                        binding.tvChangePass.setVisibility(View.VISIBLE)
                    }
                }
        }
        }

    }
