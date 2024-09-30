package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmiResultActivity extends AppCompatActivity {

    private TextView txtEMIResult;
    private Button btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_result);

        txtEMIResult = findViewById(R.id.txtEMIResult);
        btnBack = findViewById(R.id.btnBack);

        // Get the EMI result from the Intent extras
        Intent intent = getIntent();
        double emi = intent.getDoubleExtra("emi_value", 0.0);

        // Display the EMI value
        txtEMIResult.setText("Your Monthly EMI: " + String.format("%.2f", emi));

        // Back button to return to the main activity
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();  // Finish the current activity and go back
            }
        });
    }
}

