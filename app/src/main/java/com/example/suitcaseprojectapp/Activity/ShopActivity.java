package com.example.suitcaseprojectapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.suitcaseprojectapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShopActivity extends AppCompatActivity {

    FloatingActionButton fab_button_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        fab_button_shop=findViewById(R.id.fab_button_shop);
        // go to add Product
        fab_button_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddProduct(v);
            }
        });
    }

    private void goToAddProduct(View view ){
        Intent addProduct= new Intent(ShopActivity.this,AddProductActivity.class);
        startActivity(addProduct);
        finish();
    }
}