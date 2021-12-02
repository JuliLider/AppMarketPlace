package com.example.appmarketplace

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appmarketplace.databinding.ActivityWelcomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
    GOOGLE
}

class WelcomeActivity : AppCompatActivity() {
    private lateinit var appbarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarWelcome.myToolbar)

        binding.appBarWelcome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.text_fab, Snackbar.LENGTH_LONG).show()
        }

        val drawerLayout:DrawerLayout = binding.drawerLayout
        val navView:NavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        appbarConfiguration = AppBarConfiguration(setOf(R.id.nav_todo, R.id.nav_about), drawerLayout)

        setupActionBarWithNavController(navController, appbarConfiguration)

        navView.setupWithNavController(navController)

       /* if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, ToDoFragment::class.java, null, "todo")
                .commit()
        }*/
        //SETUP
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")


        // SAVE DATA GOOGLE
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp(appbarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_welcome_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId){

        R.id.action_settings ->{
            Toast.makeText(this, R.string.text_action_home, Toast.LENGTH_LONG).show()
            val intento = Intent(this, WelcomeActivity::class.java).apply {}
            startActivity(intento)
            true
        }
        R.id.action_logout ->{
            onSignoff();
            val intento = Intent(this, LoginActivity::class.java).apply {  }
            Toast.makeText(this, R.string.text_action_logout, Toast.LENGTH_LONG).show()
            startActivity(intento)
            true
        }
        else ->{

            super.onOptionsItemSelected(item)
        }
    }
    fun onSignoff() {

        //Delete Data
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()

        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }
}