package com.example.appmarketplace

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private var edtPassword: EditText? = null
    private var edtUsername: EditText? = null

    private val GOOGLE_SING_IN = 100

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

        title = "Home"

        session()

    }

    override fun onStart() {
        super.onStart()

        val prefs = getSharedPreferences(resources.getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email == null && provider == null) {
            var loginLayout = findViewById<LinearLayout>(R.id.loginLayout);
            loginLayout.visibility = View.VISIBLE
        }


    }

    private fun session() {
        val prefs =
            getSharedPreferences(resources.getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            var loginLayout = findViewById<LinearLayout>(R.id.loginLayout);
            loginLayout.visibility = View.INVISIBLE

            showWelcome(email, ProviderType.valueOf(provider))
        }

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

    private fun showWelcome(email: String, Provider: ProviderType){
        val welcomeIntent = Intent(this, WelcomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("Provider", Provider.toString())
        }
        startActivity(welcomeIntent)
    }

    private fun getToast(message: String) {
        Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show();
    }

    fun googleLogin(view: android.view.View) {

        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.default_web_client_id2))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut()
        startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SING_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credencial).addOnCompleteListener {
                            if (it.isSuccessful) {
                                showWelcome(account.email ?: "", ProviderType.GOOGLE)
                            } else {
                                getToast(resources.getString(R.string.text_errorAuth));
                            }
                        }
                }
            } catch (e: ApiException) {
                getToast(resources.getString(R.string.text_errorAuth));
            }


        }
    }


}
