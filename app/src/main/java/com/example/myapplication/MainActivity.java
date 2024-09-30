package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private EditText edtMortgageAmount, edtTenure, edtInterestRate;
    private Button btnCalculateEMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        edtMortgageAmount = findViewById(R.id.edtMortgageAmount);
        edtTenure = findViewById(R.id.edtTenure);
        edtInterestRate = findViewById(R.id.edtInterestRate);
        btnCalculateEMI = findViewById(R.id.btnCalculateEMI);

        // Set button click listener
        btnCalculateEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        try {
            // Check if any of the input fields are empty
            if (edtMortgageAmount.getText().toString().isEmpty() ||
                    edtTenure.getText().toString().isEmpty() ||
                    edtInterestRate.getText().toString().isEmpty()) {

                Toast.makeText(MainActivity.this, "Please enter all required values.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get input values
            double principal = Double.parseDouble(edtMortgageAmount.getText().toString());
            int tenureInYears = Integer.parseInt(edtTenure.getText().toString());
            double annualInterestRate = Double.parseDouble(edtInterestRate.getText().toString());

            // Check for invalid values
            if (principal <= 0 || tenureInYears <= 0 || annualInterestRate <= 0) {
                Toast.makeText(MainActivity.this, "Please enter valid positive numbers.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert annual interest rate to monthly and tenure to months
            double monthlyInterestRate = (annualInterestRate / 12) / 100;
            int tenureInMonths = tenureInYears * 12;

            // EMI Calculation formula
            double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureInMonths))
                    / (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1);

            // Create an Intent to start the EMIResultActivity
            Intent intent = new Intent(MainActivity.this, EmiResultActivity.class);
            intent.putExtra("emi_value", emi);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Please enter valid numeric values.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

