package com.example.suitcaseprojectapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.suitcaseprojectapp.Database.DbHelper;
import com.example.suitcaseprojectapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {

    private final int GALLERY_CODE = 1;
    private final int CAMERA_CODE = 2;
    private ImageView imageView_product_add, back_btn_product_add;

    private Button image_from_camera_btn, from_gallery_btn;

    private TextInputEditText editText_product_name_add, editText_product_price_add;
    private TextInputEditText editText_product_description_add, editText_product_location_add;

    private FloatingActionButton fab_add_product;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // database connection
        dbHelper = new DbHelper(this);

        // imageview
        imageView_product_add = findViewById(R.id.imageView_product_add);
        back_btn_product_add = findViewById(R.id.back_btn_product_add);


        // edittext
        editText_product_name_add = findViewById(R.id.editText_product_name_add);
        editText_product_price_add = findViewById(R.id.editText_product_price_add);
        editText_product_description_add = findViewById(R.id.editText_product_description_add);
        editText_product_location_add = findViewById(R.id.editText_product_location_add);

        // floating action btn
        fab_add_product = findViewById(R.id.fab_add_product);

        // image button
        image_from_camera_btn = findViewById(R.id.image_from_camera_btn);
        from_gallery_btn = findViewById(R.id.from_gallery_btn);

        from_gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery, GALLERY_CODE);
            }
        });

        image_from_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera, CAMERA_CODE);
            }
        });


        fab_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDataInProduct();
            }
        });
    }


    private void goBackToShop(View view) {
        Intent shopIntent = new Intent(AddProductActivity.this, ShopActivity.class);
        startActivity(shopIntent);
        finish();
    }


    // converting image
    public byte[] convertImageViewToByteArray(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CODE) {
                //for camera
                Bitmap img = (Bitmap) (data.getExtras().get("data"));
                imageView_product_add.setImageBitmap(img);
            } else if (requestCode == GALLERY_CODE) {
                //for gallery
                imageView_product_add.setImageURI(data.getData());
            }
        }
    }


    private void insertDataInProduct() {

        String name = editText_product_name_add.getText().toString().trim();
        String price = editText_product_price_add.getText().toString().trim();
        String des = editText_product_description_add.getText().toString().trim();
        String location = editText_product_location_add.getText().toString().trim();
        byte[] img = convertImageViewToByteArray(imageView_product_add);
        String isPurchase = "false";

        dbHelper = new DbHelper(this);
        // Check if any of the fields is empty
        if (name.isEmpty() || price.isEmpty() || des.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please provide all required values", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert the data into the database
        long res = dbHelper.productadd(name, price, des, img, location, isPurchase);

        if (res != -1) {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            editText_product_name_add.setText("");
            editText_product_price_add.setText("");
            editText_product_description_add.setText("");
            editText_product_location_add.setText("");

        } else {
            Toast.makeText(this, "Error inserting data", Toast.LENGTH_SHORT).show();
        }

    }

}