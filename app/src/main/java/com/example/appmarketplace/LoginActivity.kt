package com.example.appmarketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var edtPassword: EditText? = null
    private var edtUsername: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(
            resources.getString(R.string.fire_base),
            resources.getString(R.string.fire_base_message)
        )
        analytics.logEvent(resources.getString(R.string.go_markerplace), bundle)

        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);


        session()

    }

    private fun session() {

    }

    fun onLogin(view: android.view.View) {
        var username = edtUsername!!.text.toString();
        var password = edtPassword!!.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username,
                    password).addOnCompleteListener {
                    if(it.isSuccessful){
                        showWelcome(it.result?.user?.email?: "", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
        }
    }

    fun onRegister(view: android.view.View) {
        var username = edtUsername!!.text.toString();
        var password = edtPassword!!.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(username,
                    password).addOnCompleteListener {
                    if(it.isSuccessful){
                        showWelcome(it.result?.user?.email?: "", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha presentado un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showWelcome(email: String, provider: ProviderType){
        val welcomeIntent = Intent(this, WelcomeActivity::class.java).apply {

        }
        startActivity(welcomeIntent)
    }

    fun googleLogin(view: android.view.View) {


    }



}
