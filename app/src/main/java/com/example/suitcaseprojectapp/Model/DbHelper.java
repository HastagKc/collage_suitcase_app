package com.example.suitcaseprojectapp.Model;

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

        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_USER + " WHERE email='" + email1 + "'AND password='" + pass1 + "' ", null);
        if (cursor.moveToFirst()) {
            loggedIn = true;

        } else {
            loggedIn = false;
        }
        return loggedIn;
    }
}
