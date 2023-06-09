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
import com.example.jumpeak.adapter.ProfessionAdapter
import com.example.jumpeak.model.HardSkill
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HardSkillActivity : AppCompatActivity() , HardSkillAdapter.Listener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var hardskillList:ArrayList<HardSkill>
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_skill)
        supportActionBar?.hide();

        val tvBack=findViewById<TextView>(R.id.tvBackProfession)
        tvBack.setOnClickListener{startActivity(Intent(this,ProfessionActivity::class.java))}

        val btnNextActivity=findViewById<Button>(R.id.btnHardSkill)
        btnNextActivity.setOnClickListener { startActivity(Intent(this,SoftSkillActivity::class.java)) }

        recyclerView=findViewById(R.id.rvHardSkill)
        recyclerView.layoutManager= LinearLayoutManager(this)

        hardskillList= arrayListOf()
        db= FirebaseFirestore.getInstance()
        db.collection("HardSkills").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val hardskill: HardSkill? =data.toObject(HardSkill::class.java)
                        if(hardskill!=null){
                            hardskillList.add(hardskill)
                        }
                    }
                    recyclerView.adapter= HardSkillAdapter(hardskillList,this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()}

    }
    override fun onClick(hardskill: HardSkill) {
    }
}