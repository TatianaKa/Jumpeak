package com.example.jumpeak.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.R

class RegFirstPassActivity : AppCompatActivity() {
    private lateinit var etPass: EditText
    private lateinit var tvWrongPass: TextView
    private lateinit var btnFirstPass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_first_pass)
        supportActionBar?.hide();
        init()
    }
    fun init(){
            btnFirstPass=findViewById(R.id.btnFirstPass)
            etPass=findViewById(R.id.etFirstPass)
            tvWrongPass=findViewById(R.id.tvWrongPass)
            etPass.setOnFocusChangeListener{view, b -> etPass.setBackgroundResource(R.drawable.shape_purple)}
            //val str = intent.getStringExtra("email")
            //tvEnterPass.setText(str)
            etPass.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    btnFirstPass.setOnClickListener{Next(s.toString()) }

                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    etPass.setBackgroundResource(R.drawable.shape_purple)
                }
            })
        }
    fun Next(password:String){
        val pass = intent.getStringExtra("pass").toString()
        val email = intent.getStringExtra("email").toString()
        val intent:Intent=Intent(this,RegSecondPassActivity::class.java)
        tvWrongPass.setText(email)
        if(TextUtils.isEmpty(password)){
            tvWrongPass.setText("\uD83D\uDE14, пустое значение пароля")
            tvWrongPass.setVisibility(View.VISIBLE)
        }
        if(password!=pass){
            etPass.setBackgroundResource(R.drawable.shape_red)
            tvWrongPass.setVisibility(View.VISIBLE)
            Toast.makeText(this@RegFirstPassActivity, "wrong pass",Toast.LENGTH_SHORT)
        }
        else{
            tvWrongPass.setVisibility(View.INVISIBLE)
            etPass.setBackgroundResource(R.drawable.shape_purple)
            intent.putExtra("email",email)
            startActivity(intent)
        }
    }

}
