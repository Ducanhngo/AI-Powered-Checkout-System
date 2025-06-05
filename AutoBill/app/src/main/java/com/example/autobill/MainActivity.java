package com.example.autobill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button UsersButton, TransactionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersButton = findViewById(R.id.users_button);
        TransactionsButton = findViewById(R.id.transactions_button);

        UsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến User List
                Intent intent = new Intent(MainActivity.this, UsersInformation.class);
                startActivity(intent);
            }
        });

        TransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến Transaction History
                Intent intent = new Intent(MainActivity.this, com.example.autobill.db.TransactionHistory.class);
                startActivity(intent);
            }
        });
    }
}