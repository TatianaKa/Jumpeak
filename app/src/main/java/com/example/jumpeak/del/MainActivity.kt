package com.example.jumpeak.del

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.R
import com.example.jumpeak.reg.RegFirstPassActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tvOne=findViewById<TextView>(R.id.tvOne)
        val tvTwo=findViewById<TextView>(R.id.tvTwo)
        val tvThree=findViewById<TextView>(R.id.tvThree)
        tvOne.text=intent.getStringExtra("pass").toString()
        tvTwo.text=intent.getStringExtra("email").toString()
        tvThree.text=intent.getStringExtra("reg").toString()

    }
}