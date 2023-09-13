package com.example.suitcaseprojectapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.suitcaseprojectapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MessageActivity extends AppCompatActivity {
    private ImageView camera_mes_image, backBtn;
    private Button mes_camera_btn;

    private FloatingActionButton sent_msg_btn;

    // request code for camera
    private final int CAMERA_REQ_CODE = 100;

    // sms code
    private final int SMS_REQ_CODE = 110;

    Bitmap imgUrl;

    // TextEditLayout
    private TextInputEditText productEditText_mes, productPriceEditText_mes;
    private TextInputEditText phoneNumberEditText_mes, messageEditText_mes, productLocationEditText_mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        // back btn
        backBtn = findViewById(R.id.mes_back_btn);
        // camera
        camera_mes_image = findViewById(R.id.camera_mes_image);
        mes_camera_btn = findViewById(R.id.mes_camera_btn);

        // textEditLayout

        productEditText_mes = findViewById(R.id.productEditText_mes);
        productPriceEditText_mes = findViewById(R.id.productPriceEditText_mes);
        phoneNumberEditText_mes = findViewById(R.id.phoneNumberEditText_mes);
        messageEditText_mes = findViewById(R.id.messageEditText_mes);
        productLocationEditText_mes = findViewById(R.id.productLocationEditText_mes);

        // floating action btn
        sent_msg_btn = findViewById(R.id.sent_msg_btn);

        // go back to dashboard
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToDashboard(v);
            }
        });


        //open camera
        mes_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, CAMERA_REQ_CODE);
            }
        });

        // SEND SMS
        sent_msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg(v);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA_REQ_CODE) {
                // for camera
                Bitmap img = (Bitmap) data.getExtras().get("data");

                imgUrl = img;

                //camera_mes_image.setImageBitmap(img);

                // Create a BitmapDrawable from the captured Bitmap
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), img);

                // Set the BitmapDrawable as the background of the ImageView
                camera_mes_image.setBackground(bitmapDrawable);


            }
        }

    }

    private void goBackToDashboard(View view) {
        Intent dash = new Intent(MessageActivity.this, DashboardActivity.class);
        startActivity(dash);
        finish();
    }

    void smsCode() {
        String product_name = productEditText_mes.getText().toString().trim();
        String price = productPriceEditText_mes.getText().toString().trim();
        String phone = phoneNumberEditText_mes.getText().toString().trim();
        String msg = messageEditText_mes.getText().toString().trim();
        String location = productLocationEditText_mes.getText().toString().trim();
        if (phone.equals("") && msg.equals("")) {
            String full_msg = "Product Name: " + product_name + "\nPrice: " + price + "\nDescription: " + msg + "\nLocation: " + location + "\nImgUrl: " + imgUrl;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, full_msg, null, null);

            Toast.makeText(this, "Message is sent Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Message can't send without any number !!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMsg(View view) {
        if (ContextCompat.checkSelfPermission(MessageActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            smsCode();
        } else {

            ActivityCompat.requestPermissions(MessageActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && SMS_REQ_CODE == 110) {
            smsCode();
        }
    }
}