package com.example.irina.recipebook.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.irina.recipebook.logics.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina on 27.11.2016.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recipesManager";
    private static final String TABLE_RECIPES = "recipes";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COOKING = "cooking";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_TYPE = "type";
    private static final String KEY_IMAGE = "image";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT,"
                + KEY_INGREDIENTS + " TEXT,"
                + KEY_COOKING + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

        onCreate(db);
    }

    public void addContact(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_TYPE, recipe.getType());
        values.put(KEY_INGREDIENTS, recipe.getIngredients());
        values.put(KEY_COOKING, recipe.getCooking());
        values.put(KEY_IMAGE, recipe.getImage());

        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public Recipe getRecipe(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPES, new String[] { KEY_ID,
                        KEY_NAME, KEY_TYPE, KEY_INGREDIENTS, KEY_COOKING, KEY_IMAGE }, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Recipe recipe = new Recipe(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> contactList = new ArrayList<Recipe>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setType(cursor.getString(2));
                recipe.setIngredients(cursor.getString(3));
                recipe.setCooking(cursor.getString(4));
                recipe.setImage(cursor.getString(5));

                contactList.add(recipe);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_TYPE, recipe.getType());
        values.put(KEY_INGREDIENTS, recipe.getIngredients());
        values.put(KEY_COOKING, recipe.getCooking());
        values.put(KEY_IMAGE, recipe.getImage());

        return db.update(TABLE_RECIPES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(recipe.getId()) });
    }

    public void deleteRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES, KEY_ID + " = ?", new String[] { String.valueOf(recipe.getId()) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES, null, null);
        db.close();
    }

    public int getRecipeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}