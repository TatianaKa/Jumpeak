package com.example.jumpeak.Auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jumpeak.ClassHelper.User
import com.example.jumpeak.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegMailActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var tvNotFound: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var btnNext: Button
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_mail)
        supportActionBar?.hide();
        Init()
    }
    fun Init(){
        etEmail=findViewById(R.id.etRegEmail)
        btnNext=findViewById(R.id.btnRegNext)
        tvNotFound=findViewById(R.id.tvNotFound)

        etEmail.setOnFocusChangeListener{view, b -> etEmail.setBackgroundResource(R.drawable.shape_purple)}
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnNext.setOnClickListener{Next(s.toString()) }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etEmail.setBackgroundResource(R.drawable.shape_purple)
            }
        })
    }
    fun Next(email:String){
        val intent:Intent=Intent(this,RegFirstPassActivity::class.java)
        if(TextUtils.isEmpty(email)){
            etEmail.setBackgroundResource(R.drawable.shape_red)
            Toast.makeText(this@RegMailActivity,"Empty value",Toast.LENGTH_SHORT).show()
        }
        else{
            db.collection("UnregistedUsers")
                .whereEqualTo("email",email).get()
                .addOnSuccessListener {
                    var result:StringBuffer=StringBuffer()
                    var isReg:StringBuffer=StringBuffer()

                    for (document in it){
                        result.append(document.data.getValue("password"))
                        isReg.append(document.data.getValue("isRegistred"))
                    }
                    if(TextUtils.isEmpty(result.toString())){
                        etEmail.setBackgroundResource(R.drawable.shape_red)
                        tvNotFound.setVisibility(View.VISIBLE)
                    }
                    else if(isReg.toString()=="true"){
                        etEmail.setBackgroundResource(R.drawable.shape_red)
                        tvNotFound.setText("данный аккаунт уже зарегистрирован")
                        tvNotFound.setVisibility(View.VISIBLE)
                    }
                    else{
                        tvNotFound.setVisibility(View.INVISIBLE)
                        intent.putExtra("email",email)
                        intent.putExtra("pass", result.toString())
                        startActivity(intent)
                    }
                }
                .addOnFailureListener { exception ->
                }

        }
    }
}