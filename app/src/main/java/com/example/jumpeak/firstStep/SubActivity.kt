package com.example.jumpeak.firstStep

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.R
import com.example.jumpeak.adapter.ProfessionAdapter
import com.example.jumpeak.adapter.SubAdapter
import com.example.jumpeak.adapter.SubjectAdapter
import com.example.jumpeak.model.Profession
import com.example.jumpeak.model.Sub
import com.example.jumpeak.model.Subject
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SubActivity : AppCompatActivity(), SubAdapter.Listener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var subList:ArrayList<Sub>
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        supportActionBar?.hide();

        val tvBack=findViewById<TextView>(R.id.tvBackLetsGo)
        tvBack.setOnClickListener{startActivity(Intent(this,LetsGoActivity::class.java))}

        recyclerView=findViewById(R.id.rvSubject)
        recyclerView.layoutManager= LinearLayoutManager(this)

        subList= arrayListOf()
        db= FirebaseFirestore.getInstance()
        db.collection("Subject").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val sub: Sub? =data.toObject(Sub::class.java)
                        if(sub!=null){
                            subList.add(sub)
                        }
                    }
                    recyclerView.adapter= SubAdapter(subList,this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()}

        val btnNextActivity=findViewById<Button>(R.id.btnSubject)
        btnNextActivity.setOnClickListener { startActivity(Intent(this,ProfessionActivity::class.java)) }
    }
    override fun onClick(sub: Sub) {
    }

}