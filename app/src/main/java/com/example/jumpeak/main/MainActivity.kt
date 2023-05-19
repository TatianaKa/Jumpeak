package com.example.jumpeak.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.jumpeak.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController=findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }
}