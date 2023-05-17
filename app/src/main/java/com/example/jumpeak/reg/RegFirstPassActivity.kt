package com.example.jumpeak.reg

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jumpeak.R


class RegFirstPassActivity : AppCompatActivity() {
    private lateinit var etFirst: EditText
    private lateinit var etSecond: EditText
    private lateinit var etThird: EditText
    private lateinit var etFourth: EditText
    private lateinit var etFifth: EditText
    private lateinit var btnFirstPass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_first_pass)
        supportActionBar?.hide();
        init()
    }
    fun init(){
        var pass:String=""

        etFirst=findViewById(R.id.etFirstSymbol)
        etSecond=findViewById(R.id.etSecondSymbol)
        etThird=findViewById(R.id.etThirdSymbol)
        etFourth=findViewById(R.id.etFourthSymbol)
        etFifth=findViewById(R.id.etFifthSymbol)

        btnFirstPass=findViewById(R.id.btnFirstPass)


        etFirst.setOnFocusChangeListener{view, b -> etFirst.setBackgroundResource(R.drawable.shape_purple)}
        etSecond.setOnFocusChangeListener{view, b -> etSecond.setBackgroundResource(R.drawable.shape_purple)}
        etThird.setOnFocusChangeListener{view, b -> etThird.setBackgroundResource(R.drawable.shape_purple)}
        etFourth.setOnFocusChangeListener{view, b -> etFourth.setBackgroundResource(R.drawable.shape_purple)}
        etFifth.setOnFocusChangeListener{view, b -> etFifth.setBackgroundResource(R.drawable.shape_purple)}

            etFirst.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    pass+=s.toString()
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Selection.setSelection(etFirst.getText() as Editable, etSecond.getSelectionStart())
                    etSecond.requestFocus()
                }
            })
            etSecond.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    pass+=s.toString()
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Selection.setSelection(etSecond.getText() as Editable, etThird.getSelectionStart())
                    etThird.requestFocus()
                }
            })
            etThird.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    pass+=s.toString()
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Selection.setSelection(etThird.getText() as Editable, etFourth.getSelectionStart())
                    etFourth.requestFocus()
                }
            })
            etFourth.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    pass+=s.toString()
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Selection.setSelection(etFourth.getText() as Editable, etFifth.getSelectionStart())
                    etFifth.requestFocus()
                }
            })
            etFifth.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    pass+=s.toString()
                    btnFirstPass.setOnClickListener{Next(pass) }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    fun Next(password:String){
        val pass = intent.getStringExtra("pass").toString()
        val email = intent.getStringExtra("email").toString()
        val intent:Intent=Intent(this, RegSecondPassActivity::class.java)
        if(password!=pass){
            Toast.makeText(this@RegFirstPassActivity,"Wrong pass",Toast.LENGTH_SHORT).show()
        }
        else{
            intent.putExtra("email",email)
            startActivity(intent)
        }
    }

}
