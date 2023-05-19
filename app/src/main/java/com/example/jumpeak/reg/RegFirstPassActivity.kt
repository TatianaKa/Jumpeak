package com.example.jumpeak.reg

import android.annotation.SuppressLint
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
import com.example.jumpeak.databinding.ActivityRegFirstPassBinding


class RegFirstPassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegFirstPassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegFirstPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide();
        init()
    }
    fun init(){
        var pass:String=""
        binding.etFirstSymbol.setOnFocusChangeListener{view, b -> binding.etFirstSymbol.setBackgroundResource(R.drawable.shape_purple)}
        binding.etSecondSymbol.setOnFocusChangeListener{view, b -> binding.etSecondSymbol.setBackgroundResource(R.drawable.shape_purple)}
        binding.etThirdSymbol.setOnFocusChangeListener{view, b -> binding.etThirdSymbol.setBackgroundResource(R.drawable.shape_purple)}
        binding.etFourthSymbol.setOnFocusChangeListener{view, b -> binding.etFourthSymbol.setBackgroundResource(R.drawable.shape_purple)}
        binding.etFifthSymbol.setOnFocusChangeListener{view, b -> binding.etFifthSymbol.setBackgroundResource(R.drawable.shape_purple)}
        binding.btnFirstPass.setOnClickListener { CheckCorrectPass() }
        binding.etFirstSymbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Selection.setSelection(binding.etFirstSymbol.getText() as Editable, binding.etSecondSymbol.getSelectionStart())
                binding.etSecondSymbol.requestFocus()
            }
        })
        binding.etSecondSymbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Selection.setSelection(binding.etSecondSymbol.getText() as Editable, binding.etThirdSymbol.getSelectionStart())
                binding.etThirdSymbol.requestFocus()
            }
        })
        binding.etThirdSymbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Selection.setSelection(binding.etThirdSymbol.getText() as Editable, binding.etFourthSymbol.getSelectionStart())
                binding.etFourthSymbol.requestFocus()
            }
        })
        binding.etFourthSymbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Selection.setSelection(binding.etFourthSymbol.getText() as Editable, binding.etFifthSymbol.getSelectionStart())
                binding.etFifthSymbol.requestFocus()
            }
        })
        binding.etFifthSymbol.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.btnFirstPass.setOnClickListener{CheckCorrectPass() }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnFirstPass.setOnClickListener{CheckCorrectPass() }
            }
        })
        }

    fun CheckCorrectPass(){
        val passFromDatabase = intent.getStringExtra("pass").toString()
        val email = intent.getStringExtra("email").toString()

        var pass:String=""

        var firstSymbol=binding.etFirstSymbol.text.toString()
        var secondSymbol=binding.etSecondSymbol.text.toString()
        val thirdSymbol=binding.etThirdSymbol.text.toString()
        var fourthSymbol=binding.etFourthSymbol.text.toString()
        var fifthSymbol=binding.etFifthSymbol.text.toString()
        if(TextUtils.isEmpty(firstSymbol) || TextUtils.isEmpty(secondSymbol) ||
            TextUtils.isEmpty(thirdSymbol) || TextUtils.isEmpty(fourthSymbol) ||
            TextUtils.isEmpty(fifthSymbol)) Toast.makeText(this, "Empty value", Toast.LENGTH_SHORT).show()
        else {
            pass+=firstSymbol
            pass+=secondSymbol
            pass+=thirdSymbol
            pass+=fourthSymbol
            pass+=fifthSymbol

            val intent:Intent=Intent(this, RegSecondPassActivity::class.java)
            if(passFromDatabase.count()<5) Toast.makeText(this, "Empty value", Toast.LENGTH_SHORT).show()
            else if(passFromDatabase!=passFromDatabase){
                Toast.makeText(this@RegFirstPassActivity,"Wrong pass",Toast.LENGTH_SHORT).show()
            }
            else{
                intent.putExtra("email",email)
                startActivity(intent)
            }
        }


    }

}
