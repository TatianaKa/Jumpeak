package com.example.jumpeak.firstStep

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpeak.R
import com.example.jumpeak.adapter.HardSkillAdapter
import com.example.jumpeak.adapter.SoftSkillAdapter
import com.example.jumpeak.databinding.ActivityHardSkillBinding
import com.example.jumpeak.databinding.ActivitySoftSkillBinding
import com.example.jumpeak.model.HardSkill
import com.example.jumpeak.model.SoftSkill
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SoftSkillActivity : AppCompatActivity() , SoftSkillAdapter.Listener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var softSkillList:ArrayList<SoftSkill>
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_skill)
        supportActionBar?.hide();

        val tvBack=findViewById<TextView>(R.id.tvBackHardSkill)
        tvBack.setOnClickListener{startActivity(Intent(this,HardSkillActivity::class.java))}

        val btnNextActivity=findViewById<Button>(R.id.btnSoftSkill)
        btnNextActivity.setOnClickListener { startActivity(Intent(this,PlaceWorkActivity::class.java)) }

        recyclerView=findViewById(R.id.rvSoftSkill)
        recyclerView.layoutManager= LinearLayoutManager(this)

        softSkillList= arrayListOf()
        db= FirebaseFirestore.getInstance()
        db.collection("SoftSkills").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val softSkill: SoftSkill? =data.toObject(SoftSkill::class.java)
                        if(softSkill!=null){
                            softSkillList.add(softSkill)
                        }
                    }
                    recyclerView.adapter= SoftSkillAdapter(softSkillList,this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()}

    }
    override fun onClick(softSkill: SoftSkill) {
    }
}