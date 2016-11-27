package com.example.irina.recipebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowRecipeActivity extends AppCompatActivity {

    ListView recipesList;
    String[] array = {"qwe", "asd", "zxc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String recipeType = getIntent().getStringExtra("type");
        setTitle(recipeType);

        recipesList = (ListView) findViewById(R.id.recipes_list);

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));

        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);

        recipesList.setAdapter(adapter);

        recipesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ShowRecipeActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    }



