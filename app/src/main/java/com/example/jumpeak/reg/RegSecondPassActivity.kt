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
import com.example.jumpeak.firstStep.LetsGoActivity
import com.google.firebase.auth.FirebaseAuth

class RegSecondPassActivity : AppCompatActivity() {
    private lateinit  var etPass: EditText
    private lateinit  var tvChangePass: TextView
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
        tvChangePass=findViewById(R.id.tvChangePass)
        auth= FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email").toString()

        etPass.setOnFocusChangeListener{view, b -> etPass.setBackgroundResource(R.drawable.shape_purple)}

        etPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if( TextUtils.isEmpty(s)){
                    tvChangePass.setText("\uD83D\uDE14, пустое значение пароля")
                    tvChangePass.setVisibility(View.VISIBLE)
                }
                else if (s != null) {
                    if(s.count()<6){
                        Toast.makeText(this@RegSecondPassActivity,"Count letters small",Toast.LENGTH_SHORT).show()
                    }
                    else btnCreate.setOnClickListener{createNewAccount((s.toString()))}
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        //переделать цвет указателя?
    }
    fun createNewAccount(password:String) {
            val email = intent.getStringExtra("email").toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Пользователь успешно добавлен",
                            Toast.LENGTH_SHORT).show()
                            tvChangePass.setVisibility(View.INVISIBLE)
                            startActivity(Intent(this@RegSecondPassActivity, LetsGoActivity::class.java))

                    } else {
                        Toast.makeText(this, "Пользователь не добавлен",
                            Toast.LENGTH_SHORT).show()
                        tvChangePass.setText(email)
                        tvChangePass.setVisibility(View.VISIBLE)
                    }
                }
        }

    }
