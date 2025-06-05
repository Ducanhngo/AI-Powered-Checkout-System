
package com.example.spendingmanager.purchase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendingmanager.R // Import R for drawable
import com.example.spendingmanager.databinding.ActivityPurchaseBinding // Import View Binding class
import kotlin.random.Random

class PurchaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseBinding
    private var generatedConfirmationCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initially hide confirmation and result sections
        binding.layoutConfirmationCode.visibility = View.GONE
        binding.layoutTransactionResult.visibility = View.GONE
        binding.progressBarPurchase.visibility = View.GONE

        binding.buttonSimulateScan.setOnClickListener {
            simulateRfidScan()
        }

        binding.buttonConfirmPurchase.setOnClickListener {
            verifyConfirmationCode()
        }

        binding.buttonBackToDashboard.setOnClickListener {
            finish() // Close this activity and return to the previous one (Dashboard)
        }
    }

    private fun simulateRfidScan() {
        // Disable scan button to prevent multiple clicks
        binding.buttonSimulateScan.isEnabled = false
        binding.progressBarPurchase.visibility = View.VISIBLE

        // Simulate a short delay for the scan/code generation
        Handler(Looper.getMainLooper()).postDelayed({
            // Generate a 6-digit confirmation code
            generatedConfirmationCode = String.format("%06d", Random.nextInt(1000000))

            // Simulate sending the code (Log it and show a Toast)
            Log.d("PurchaseActivity", "Generated Confirmation Code: $generatedConfirmationCode (Simulated Send)")
            Toast.makeText(this, "Confirmation code generated (Simulated Send): $generatedConfirmationCode", Toast.LENGTH_LONG).show()

            // Update UI
            binding.progressBarPurchase.visibility = View.GONE
            binding.layoutConfirmationCode.visibility = View.VISIBLE
            binding.editTextConfirmationCode.requestFocus()

        }, 1500) // 1.5 second delay
    }

    private fun verifyConfirmationCode() {
        val enteredCode = binding.editTextConfirmationCode.text.toString().trim()

        if (enteredCode.isEmpty()) {
            binding.editTextConfirmationCode.error = "Code is required"
            binding.editTextConfirmationCode.requestFocus()
            return
        }

        if (enteredCode != generatedConfirmationCode) {
            binding.editTextConfirmationCode.error = "Invalid confirmation code"
            binding.editTextConfirmationCode.requestFocus()
            Toast.makeText(this, "Incorrect code. Please try again.", Toast.LENGTH_SHORT).show()
            return
        }

        // Code is correct, simulate transaction finalization
        binding.progressBarPurchase.visibility = View.VISIBLE
        binding.layoutConfirmationCode.visibility = View.GONE // Hide code input

        // Simulate transaction processing delay
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBarPurchase.visibility = View.GONE

            // Show success message and icon
            binding.imageViewResultIcon.setImageResource(R.drawable.ic_success) // Make sure ic_success exists
            binding.textViewResultMessage.text = "Purchase Successful! Locker Opened (Simulated)."
            binding.layoutTransactionResult.visibility = View.VISIBLE

            // TODO: In a real app, here you would:
            // 1. Record the transaction in Firestore
            // 2. Update the user's spending total
            // 3. Communicate with the locker hardware (if applicable)

        }, 2000) // 2 second delay
    }

    // Need a placeholder drawable for success icon
    // Create /res/drawable/ic_success.xml
}

