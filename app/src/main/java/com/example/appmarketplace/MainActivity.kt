package com.example.appmarketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
    }

    fun onLogin(view: android.view.View) {
        if(edtUsername!!.text.toString()=="juli110@hotmail.es"){
            if(edtPassword!!.text.toString()=="012938"){
                val intento = Intent(this, WelcomeActivity::class.java)
                startActivity(intento)
            }
        }
    }

    fun onRegister(view: android.view.View) {

    }
}