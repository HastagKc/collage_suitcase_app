package com.example.suitcaseprojectapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suitcaseprojectapp.Model.DbHelper;
import com.example.suitcaseprojectapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText cmpasswordEditText_reg, passwordEditText_reg, emailEditText_reg, usernameEditText_reg;
    private Button registerBtn;
    private TextView goBackBtn;

    DbHelper dbHelper;


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

        // register
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);

            }
        });

    }

    public void goBackLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }


    public void registerUser(View view) {
        String name = usernameEditText_reg.getText().toString();
        String mail = emailEditText_reg.getText().toString();
        String pass = passwordEditText_reg.getText().toString();
        String cm_pass = cmpasswordEditText_reg.getText().toString();
        try {
            if (!name.equals("") && !mail.equals("") && !pass.equals("") && !cm_pass.equals("")) {
                if (pass.equals(cm_pass)) {
                    if (pass.length() > 6) {
                        // calling method with help of object
                        dbHelper = new DbHelper(this);
                        boolean b = dbHelper.registerUserHelper(name, mail, cm_pass);
                        if (b == true) {

                            Toast.makeText(this, "User is register successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            usernameEditText_reg.setText("");
                            emailEditText_reg.setText("");
                            passwordEditText_reg.setText("");
                            cmpasswordEditText_reg.setText("");

                        } else {
                            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Password length greater than 6 character", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show();
                }
            } else {

                Toast.makeText(this, "Field should not be empty", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            //  Block of code to handle errors
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}