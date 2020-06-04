package com.example.chefrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Otp extends AppCompatActivity {
    private EditText otp_number;
    private Button verify_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp_number = findViewById(R.id.otp_number);
        verify_btn = findViewById(R.id.otp_btn);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredNumber= otp_number.getText().toString();
                if(enteredNumber.length()!=4){
                    Toast.makeText(Otp.this,  "Check The digits Number", Toast.LENGTH_SHORT).show();
                } else{
                    gotoLogin();
                }
            }
        });

    }

    private void gotoLogin() {
        Intent login = new Intent(Otp.this,Login.class);
        startActivity(login);
        finish();
    }
}
