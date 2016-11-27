package com.example.irina.recipebook.db;

import android.provider.BaseColumns;

/**
 * Created by Irina on 26.11.2016.
 */

public class ShoppingListContract {

    public static final String DB_NAME = "com.example.irina.recipebook.db";
    public static final int DB_VERSION = 2;

    public class ShopListEntry implements BaseColumns {
        public static final String TABLE = "ShoppingList";
        public static final String COL_SHOP_TITLE = "title";
    }
}