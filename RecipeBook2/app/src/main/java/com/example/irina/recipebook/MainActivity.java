package com.example.irina.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings: {
                Intent intent = new Intent(MainActivity.this, FindRecipeActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.action_item1: {

                Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.action_item2: {
                Intent intent = new Intent(MainActivity.this, FavoriteRecipeActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.action_item3: {
                Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, getString(R.string.action_item3), Toast.LENGTH_LONG).show();
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}
