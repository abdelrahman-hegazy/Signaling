package com.example.signaling.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SaveHandler extends SQLiteOpenHelper {


    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_SAVE = "save";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERID = "userID";
    private static final String KEY_SHOPNAME = "shopName";
    private static final String KEY_PRODUCTNAME = "productName";
    private static final String KEY_PRICE = "price";
    private static final String KEY_OFFERS = "offers";

    public SaveHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SAVE_TABLE = "CREATE TABLE " + TABLE_SAVE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERID + " TEXT," + KEY_SHOPNAME + " TEXT,"
                + KEY_PRODUCTNAME + " TEXT UNIQUE," + KEY_PRICE + " TEXT,"
                + KEY_OFFERS + " TEXT" + ")";
        db.execSQL(CREATE_SAVE_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVE);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addShop(String userID, String shopName, String productName, String price, String offers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, userID);
        values.put(KEY_SHOPNAME, shopName);
        values.put(KEY_PRODUCTNAME, productName);
        values.put(KEY_PRICE, price);
        values.put(KEY_OFFERS, offers);


        // Inserting Row
        long id = db.insert(TABLE_SAVE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }
}
