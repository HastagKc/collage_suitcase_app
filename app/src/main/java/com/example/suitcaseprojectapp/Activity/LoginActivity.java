package com.example.suitcaseprojectapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

}