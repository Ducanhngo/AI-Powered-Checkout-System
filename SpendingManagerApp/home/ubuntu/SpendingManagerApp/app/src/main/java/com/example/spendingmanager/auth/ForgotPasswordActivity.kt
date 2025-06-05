
package com.example.spendingmanager.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendingmanager.databinding.ActivityForgotPasswordBinding // Import View Binding class
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.buttonResetPassword.setOnClickListener {
            performPasswordReset()
        }

        binding.textViewBackToLogin.setOnClickListener {
            // Navigate back to LoginActivity
            finish() // Simply finish this activity to go back
        }
    }

    private fun performPasswordReset() {
        val email = binding.editTextEmail.text.toString().trim()

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Email is required"
            binding.editTextEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Please enter a valid email"
            binding.editTextEmail.requestFocus()
            return
        }

        // Show progress bar
        binding.progressBarReset.visibility = View.VISIBLE
        binding.buttonResetPassword.isEnabled = false
        binding.textViewBackToLogin.isEnabled = false

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                // Hide progress bar
                binding.progressBarReset.visibility = View.GONE
                binding.buttonResetPassword.isEnabled = true
                binding.textViewBackToLogin.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Password reset email sent. Check your inbox.",
                        Toast.LENGTH_LONG).show()
                    // Optionally navigate back to login after a delay or automatically
                    finish() // Go back to login screen
                } else {
                    Toast.makeText(baseContext, "Failed to send reset email: ${task.exception?.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
    }
}

