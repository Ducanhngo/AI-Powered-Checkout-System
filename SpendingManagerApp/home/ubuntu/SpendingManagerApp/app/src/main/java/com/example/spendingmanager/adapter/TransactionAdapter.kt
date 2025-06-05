
package com.example.spendingmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spendingmanager.databinding.ItemTransactionBinding // Import View Binding class
import com.example.spendingmanager.model.Transaction
import java.text.NumberFormat
import java.util.Locale

class TransactionAdapter(private var transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    // ViewHolder class using View Binding
    inner class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.binding.textViewItemDetails.text = transaction.itemDetails
        // Format currency properly
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        holder.binding.textViewTransactionAmount.text = format.format(transaction.amount)
        // Optionally, you could display the date here too
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    // Function to update the list of transactions if needed
    fun updateTransactions(newTransactions: List<Transaction>) {
        transactions = newTransactions
        notifyDataSetChanged() // Consider using DiffUtil for better performance
    }
}

