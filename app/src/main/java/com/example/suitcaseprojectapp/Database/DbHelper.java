package com.example.suitcaseprojectapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    // CREATING PRIVATE VARIABLE
    private static final String DATABASE_NAME = "product_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "tbl_user";
    private static final String TABLE_PRODUCT = "tbl_product";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_USER +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "email TEXT," +
                "password TEXT )";

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT +
                "(productid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "productname TEXT," +
                "productprice TEXT," +
                "productdescription TEXT," +
                "isPurchase TEXT," +
                "productimage BLOB," +
                "location TEXT )";

        db.execSQL(CREATE_TABLE_QUERY);
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    // insert value in table
    public boolean registerUserHelper(String name, String mail, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", mail);
        contentValues.put("password", pass);

        Long l = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        sqLiteDatabase.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }

    }

    // retrieve value form table
    boolean loggedIn;

    public boolean loginHelper(String email1, String pass1) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE email='" + email1 + "'AND password='" + pass1 + "' ", null);
        if (cursor.moveToFirst()) {
            loggedIn = true;

        } else {
            loggedIn = false;
        }
        return loggedIn;
    }


    // Insert method for product
    public long productadd(String name, String price, String des, byte[] image, String purchase, String location) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("productname", name);
        contentValues.put("productprice", price);
        contentValues.put("productdescription", des);
        contentValues.put("isPurchase", purchase);
        contentValues.put("productimage", image); // Store the image as a byte array
        contentValues.put("location", location);

        return sqLiteDatabase.insert(TABLE_PRODUCT, null, contentValues);
    }

}
