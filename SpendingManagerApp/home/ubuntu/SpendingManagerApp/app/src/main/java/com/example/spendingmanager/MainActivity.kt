
package com.example.spendingmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spendingmanager.dashboard.DashboardFragment
import com.example.spendingmanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Intent
import com.example.spendingmanager.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        if (auth.currentUser == null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // If the user is signed in, load the DashboardFragment
        if (savedInstanceState == null) { // Avoid adding fragment multiple times on config change
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment())
                .commitNow()
        }

        // You can add Toolbar setup or other main activity logic here
        // For example, setting the title
        supportActionBar?.title = getString(R.string.dashboard_title)
    }

    // Optional: Add a menu for logout if needed, or keep logout in fragment
}

