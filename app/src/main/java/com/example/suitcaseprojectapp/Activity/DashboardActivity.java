package com.example.suitcaseprojectapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.suitcaseprojectapp.R;

public class DashboardActivity extends AppCompatActivity {

    private CardView home_card_view, shop_card_view, message_card_view, profile_card_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Toolbar toolbar = findViewById(R.id.toolbar_dash);
        setSupportActionBar(toolbar);

        home_card_view = findViewById(R.id.home_card_view);
        shop_card_view = findViewById(R.id.shop_card_view);
        message_card_view = findViewById(R.id.message_card_view);
        profile_card_view = findViewById(R.id.profile_card_view);

        // home
        home_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextPage(v, HomeActivity.class);
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            }
        });

        // shopping
        shop_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextPage(v, ShopActivity.class);
                Toast.makeText(getApplicationContext(), "Shop", Toast.LENGTH_SHORT).show();
            }
        });

        // message
        message_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextPage(v, MessageActivity.class);
                Toast.makeText(getApplicationContext(), "Message", Toast.LENGTH_SHORT).show();
            }
        });


        // profile
        profile_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextPage(v, ProfileActivity.class);
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void gotoNextPage(View view, Class<? extends Activity> activityClass) {

        Intent intent = new Intent(DashboardActivity.this, activityClass);
        startActivity(intent);
    }


    // attach menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}