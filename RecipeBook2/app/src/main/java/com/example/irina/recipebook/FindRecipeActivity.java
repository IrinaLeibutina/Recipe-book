package com.example.irina.recipebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FindRecipeActivity extends AppCompatActivity {

    EditText dataForSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataForSearch = (EditText) findViewById(R.id.dataForSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void Find(View view) {
        Toast.makeText(FindRecipeActivity.this, dataForSearch.getText(), Toast.LENGTH_LONG).show();
    }
}

