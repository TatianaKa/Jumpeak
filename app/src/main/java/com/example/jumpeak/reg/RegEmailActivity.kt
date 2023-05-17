package com.example.jumpeak.reg

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
import com.example.jumpeak.Activity.WelcomeActivity
import com.example.jumpeak.R
import com.example.jumpeak.del.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegEmailActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var tvNotFound: TextView
    private lateinit var tvBack: TextView
    private lateinit var btnNext: Button
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_email)
        supportActionBar?.hide();
        Init()
    }
    fun Init(){
        etEmail=findViewById(R.id.etRegEmail)

        btnNext=findViewById(R.id.btnRegNext)

        tvNotFound=findViewById(R.id.tvNotFound)
        tvBack=findViewById(R.id.tvBackRegMail)

        tvBack.setOnClickListener{startActivity(Intent(this, WelcomeActivity::class.java))}

        etEmail.setOnFocusChangeListener{view, b -> etEmail.setBackgroundResource(R.drawable.shape_purple)}

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnNext.setOnClickListener{Next(s.toString()) }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etEmail.setBackgroundResource(R.drawable.shape_purple)
                tvNotFound.setVisibility(View.INVISIBLE)
            }
        })}

    fun Next(email:String){
        var db =  FirebaseFirestore.getInstance()
        val intent: Intent = Intent(this,RegFirstPassActivity::class.java)
       //val intent: Intent = Intent(this,MainActivity::class.java)
        db.collection("UnregistedUsers")
            .whereEqualTo("email", email)
            .addSnapshotListener { documents: QuerySnapshot?, error ->
                var pass:String=""
                var isLogin:String=""
                if (documents != null) {
                    for (document in documents) {
                        pass=document.data.get("password").toString()
                        isLogin= document.data.get("isRegistred").toString()
                    }
                }
                if(pass==""){
                    etEmail.setBackgroundResource(R.drawable.shape_red)
                    tvNotFound.setVisibility(View.VISIBLE)
                }
                //??????????
                else if(isLogin=="true"){
                    etEmail.setBackgroundResource(R.drawable.shape_red)
                    tvNotFound.setText("данный аккаунт уже зарегистрирован")
                    tvNotFound.setVisibility(View.VISIBLE)
                }
                else{
                    intent.putExtra("email",email)
                    intent.putExtra("pass", pass.toString())
                    startActivity(intent)
                }
            }
        }
    }