package com.example.appmarketplace

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private var edtUsername: EditText? = null
    private var edtPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
    }

    fun onLogin(botonLogin: android.view.View) {
        var username: String= edtUsername!!.text.toString()
        var password: String= edtPassword!!.text.toString()
        if(username =="juli110@hotmail.es" && password=="012938"){
            val intento = Intent(this, WelcomeActivity::class.java)
            startActivity(intento)
            Toast.makeText(applicationContext,"WELCOME",Toast.LENGTH_LONG).show()
        }
        else {
            val dialog = AlertDialog.Builder(this).setTitle("ERROR!!")
                .setMessage("Invalid Username or Password").create().show()

            Toast.makeText(this,"Invalid Username or Password",Toast.LENGTH_SHORT).show()
        }
    }

    fun onRegister(view: android.view.View) {

    }
}
