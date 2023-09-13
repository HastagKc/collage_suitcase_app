package com.example.suitcaseprojectapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.suitcaseprojectapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText cmpasswordEditText_reg, passwordEditText_reg, emailEditText_reg, usernameEditText_reg;
    private Button registerBtn;
    private TextView goBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText_reg = findViewById(R.id.usernameEditText_reg);
        emailEditText_reg = findViewById(R.id.emailEditText_reg);
        passwordEditText_reg = findViewById(R.id.passwordEditText_reg);
        cmpasswordEditText_reg = findViewById(R.id.cmpasswordEditText_reg);

        registerBtn = findViewById(R.id.registerBtn);
        goBackBtn = findViewById(R.id.goBacKlogin_btn);



        // go back to login
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackLogin(v);
            }
        });
    }

    public void goBackLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}