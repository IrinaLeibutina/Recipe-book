package com.example.irina.recipebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Irina on 27.11.2016.
 */

public class ShoppingListDbHelper extends SQLiteOpenHelper {

    public ShoppingListDbHelper(Context context) {
        super(context, ShoppingListContract.DB_NAME, null, ShoppingListContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ShoppingListContract.ShopListEntry.TABLE + " ( " +
                ShoppingListContract.ShopListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ShoppingListContract.ShopListEntry.COL_SHOP_TITLE+ " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingListContract.ShopListEntry.TABLE);
        onCreate(db);
    }
}