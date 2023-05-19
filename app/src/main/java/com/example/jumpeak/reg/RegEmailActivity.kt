package com.example.jumpeak.reg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jumpeak.activity.WelcomeActivity
import com.example.jumpeak.R
import com.example.jumpeak.databinding.ActivityRegEmailBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegEmailActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    private lateinit var binding:ActivityRegEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide();
        Init()
    }
    fun Init(){
        binding.tvBackRegMail.setOnClickListener{startActivity(Intent(this, WelcomeActivity::class.java))}
        binding.etRegEmail.setOnFocusChangeListener{view, b -> binding.etRegEmail.setBackgroundResource(R.drawable.shape_purple)}
        binding.btnRegNext.setOnClickListener{CheckEmail() }
    }

    fun CheckEmail(){
        var email=binding.etRegEmail.text.toString()
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Empty value", Toast.LENGTH_SHORT).show()
            binding.etRegEmail.setOnFocusChangeListener{view, b -> binding.etRegEmail.setBackgroundResource(R.drawable.shape_red)}
        }
        else{
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
                        binding.etRegEmail.setBackgroundResource(R.drawable.shape_red)
                        binding.tvNotFound.setVisibility(View.VISIBLE)
                    }
                    //??????????
                    else if(isLogin=="true"){
                        binding.etRegEmail.setBackgroundResource(R.drawable.shape_red)
                        binding.tvNotFound.setText("данный аккаунт уже зарегистрирован")
                        binding.tvNotFound.setVisibility(View.VISIBLE)
                    }
                    else{
                        intent.putExtra("email",email)
                        intent.putExtra("pass", pass.toString())
                        startActivity(intent)
                    }
                }
            }
        }
    }