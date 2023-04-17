package com.example.jumpeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db.collection("UnregistedUsers")
            .whereEqualTo("email","rotapodyem143@gmail.com").get()
            .addOnSuccessListener {
                var result:StringBuffer=StringBuffer()
                for (document in it){
                    val tv=findViewById<TextView>(R.id.tvLogin)
                   tv.setText(result.append(document.data.getValue("password")))
                }
                intent.putExtra("email",result.toString())
                startActivity(intent)
            }
            .addOnFailureListener { exception ->

            }

    }
}