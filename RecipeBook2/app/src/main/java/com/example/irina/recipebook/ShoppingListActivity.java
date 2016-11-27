package com.example.irina.recipebook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.irina.recipebook.db.ShoppingListDbHelper;
import com.example.irina.recipebook.db.ShoppingListContract;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private GoogleApiClient client;
    private ShoppingListDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mTaskListView = (ListView) findViewById(R.id.shopping_list);
        mHelper = new ShoppingListDbHelper(this);
        //  updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_product:
                final EditText productEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        // Change color

                        .setTitle("Добавить продукт")
                        .setMessage("Введите продукт...")
                        .setView(productEditText)
                        .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String product = String.valueOf(productEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ShoppingListContract.ShopListEntry.COL_SHOP_TITLE, product);
                                db.insertWithOnConflict(ShoppingListContract.ShopListEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Выйти", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteProduct(View view) {
        View parent = (View) view.getParent();
        TextView productTextView = (TextView) parent.findViewById(R.id.shoppingList_title);
        String product = String.valueOf(productTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ShoppingListContract.ShopListEntry.TABLE,
                ShoppingListContract.ShopListEntry.COL_SHOP_TITLE + " = ?",
                new String[]{product});
        db.close();
        updateUI();
    }

    public void addProductToFridge(View view) {
   /*
   // Add function
    */
    }

    private void updateUI() {
        ArrayList<String> shoppingList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ShoppingListContract.ShopListEntry.TABLE,
                new String[]{ShoppingListContract.ShopListEntry._ID, ShoppingListContract.ShopListEntry.COL_SHOP_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ShoppingListContract.ShopListEntry.COL_SHOP_TITLE);
            shoppingList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.shopping_list,
                    R.id.shoppingList_title,
                    shoppingList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(shoppingList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ShoppingList Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}



