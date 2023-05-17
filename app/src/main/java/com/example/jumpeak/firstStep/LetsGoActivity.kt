package com.example.jumpeak.firstStep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jumpeak.R

class LetsGoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_go)
        val btn=findViewById<Button>(R.id.btnLetsGo)
        btn.setOnClickListener{startActivity(Intent(this,ProfessionActivity::class.java))}
    }
}