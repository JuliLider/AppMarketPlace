package com.example.appmarketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.text_fab, Snackbar.LENGTH_LONG).show()
        }

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, ToDoFragment::class.java, null, "todo")
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_welcome_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId){
        R.id.action_search ->{
            Toast.makeText(this, R.string.text_action_search, Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_settings ->{
            Toast.makeText(this, R.string.text_action_settings, Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_logout ->{
            Toast.makeText(this, R.string.text_action_logout, Toast.LENGTH_LONG).show()
            true
        }
        else ->{

            super.onOptionsItemSelected(item)
        }
    }
}