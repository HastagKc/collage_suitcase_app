package com.example.suitcaseprojectapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suitcaseprojectapp.Model.DbHelper;
import com.example.suitcaseprojectapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText_login, passwordEditText_login;

    private Button loginBtn;
    private TextView register_btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText_login = findViewById(R.id.emailEditText_login);
        passwordEditText_login = findViewById(R.id.passwordEditText_login);
        loginBtn = findViewById(R.id.loginBtn);

        register_btn_login = findViewById(R.id.register_btn_login);

        // login
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedIn(v);
            }
        });

        // go to register
        register_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister(v);
            }
        });
    }

    private void gotoRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loggedIn(View view) {
        String mail1 = emailEditText_login.getText().toString();
        String pass1 = passwordEditText_login.getText().toString();

        try {
            if (mail1.isEmpty() || pass1.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Email and Password field should not be empty", Toast.LENGTH_LONG).show();
            } else {
                // creating object of db helper class
                DbHelper dbHelper = new DbHelper(getApplicationContext());
                boolean loggedIn = dbHelper.loginHelper(mail1, pass1);
                if (loggedIn) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    emailEditText_login.setText("");
                    passwordEditText_login.setText("");
                } else {
                    Toast.makeText(LoginActivity.this, "User in not register", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

}