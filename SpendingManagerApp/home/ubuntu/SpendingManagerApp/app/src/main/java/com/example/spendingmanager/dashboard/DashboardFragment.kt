
package com.example.spendingmanager.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendingmanager.MainActivity // Assuming MainActivity hosts this
import com.example.spendingmanager.adapter.TransactionAdapter
import com.example.spendingmanager.auth.LoginActivity
import com.example.spendingmanager.databinding.FragmentDashboardBinding // Import View Binding class
import com.example.spendingmanager.model.Transaction
import com.example.spendingmanager.purchase.PurchaseActivity // Assuming PurchaseActivity for simulation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.NumberFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var transactionAdapter: TransactionAdapter
    private var mockTransactions = mutableListOf<Transaction>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        setupRecyclerView()
        loadMockData()
        updateUI()

        binding.buttonLogout.setOnClickListener {
            performLogout()
        }

        binding.buttonGoToPurchase.setOnClickListener {
            // Navigate to the simulated purchase screen
            val intent = Intent(activity, PurchaseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(mockTransactions)
        binding.recyclerViewPurchaseHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
    }

    private fun loadMockData() {
        // Create some mock transactions (replace with actual data fetching later)
        mockTransactions.clear()
        mockTransactions.add(Transaction(UUID.randomUUID().toString(), "Groceries - SuperMart", 75.50, Date(System.currentTimeMillis() - 86400000 * 2))) // 2 days ago
        mockTransactions.add(Transaction(UUID.randomUUID().toString(), "Coffee - Cafe Central", 4.75, Date(System.currentTimeMillis() - 86400000 * 1))) // 1 day ago
        mockTransactions.add(Transaction(UUID.randomUUID().toString(), "Dinner - Italian Place", 45.20, Date(System.currentTimeMillis() - 3600000 * 5))) // 5 hours ago
        mockTransactions.add(Transaction(UUID.randomUUID().toString(), "Movie Tickets", 22.00, Date())) // Now

        // Sort by date descending (most recent first)
        mockTransactions.sortByDescending { it.timestamp }
    }

    private fun updateUI() {
        // Calculate total spending
        val totalSpending = mockTransactions.sumOf { it.amount }
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        binding.textViewTotalSpendingAmount.text = format.format(totalSpending)

        // Update RecyclerView
        transactionAdapter.updateTransactions(mockTransactions)
    }

    private fun performLogout() {
        auth.signOut()
        Toast.makeText(context, "Logged out successfully.", Toast.LENGTH_SHORT).show()
        // Navigate back to Login screen
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish() // Finish the MainActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding when the view is destroyed
    }
}

