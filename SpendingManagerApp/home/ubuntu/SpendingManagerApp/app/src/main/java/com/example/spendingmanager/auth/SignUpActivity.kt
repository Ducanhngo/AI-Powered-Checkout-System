
package com.example.spendingmanager.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendingmanager.databinding.ActivitySignUpBinding // Import View Binding class
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.buttonSignUp.setOnClickListener {
            performSignUp()
        }

        binding.textViewLoginLink.setOnClickListener {
            // Navigate to LoginActivity (assuming it exists)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Optional: finish SignUpActivity so user can't go back
        }
    }

    private fun performSignUp() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

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

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is required"
            binding.editTextPassword.requestFocus()
            return
        }

        if (password.length < 6) { // Firebase requires password >= 6 chars
            binding.editTextPassword.error = "Password must be at least 6 characters"
            binding.editTextPassword.requestFocus()
            return
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConfirmPassword.error = "Confirm Password is required"
            binding.editTextConfirmPassword.requestFocus()
            return
        }

        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Passwords do not match"
            binding.editTextConfirmPassword.requestFocus()
            return
        }

        // Show progress bar
        binding.progressBarSignUp.visibility = View.VISIBLE
        binding.buttonSignUp.isEnabled = false
        binding.textViewLoginLink.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                // Hide progress bar
                binding.progressBarSignUp.visibility = View.GONE
                binding.buttonSignUp.isEnabled = true
                binding.textViewLoginLink.isEnabled = true

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Sign up successful.", Toast.LENGTH_SHORT).show()
                    // Optionally send verification email
                    // auth.currentUser?.sendEmailVerification()

                    // Navigate to Login or Main activity
                    val intent = Intent(this, LoginActivity::class.java) // Go to Login after sign up
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear back stack
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
    }
}

