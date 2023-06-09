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
import com.example.jumpeak.model.Profession
import com.example.jumpeak.adapter.ProfessionAdapter
import com.example.jumpeak.databinding.ActivityProfessionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfessionActivity (): AppCompatActivity(), ProfessionAdapter.Listener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var professionList:ArrayList<Profession>
    private var profName:String=""
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profession)
        supportActionBar?.hide();

        val tvBack=findViewById<TextView>(R.id.tvBackSubject)
        tvBack.setOnClickListener{startActivity(Intent(this,SubActivity::class.java))}

        recyclerView=findViewById(R.id.rvProfession)
        recyclerView.layoutManager= LinearLayoutManager(this)

        professionList= arrayListOf()
        db= FirebaseFirestore.getInstance()
        db.collection("Profession").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val profession: Profession? =data.toObject(Profession::class.java)
                        if(profession!=null){
                            professionList.add(profession)
                        }
                    }
                    recyclerView.adapter= ProfessionAdapter(professionList,this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()}

        val btnNextActivity=findViewById<Button>(R.id.btnProfession)
        btnNextActivity.setOnClickListener {
            val intent:Intent=Intent(this,HardSkillActivity::class.java)
            intent.putExtra("profName",profName)
            startActivity(intent)
        }

    }
    override fun onClick(profession: Profession) {
        profName= profession.name.toString()
    }
}