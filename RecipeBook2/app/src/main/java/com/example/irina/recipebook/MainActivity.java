package com.example.irina.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irina.recipebook.db.DataBaseHandler;
import com.example.irina.recipebook.logics.Recipe;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DataBaseHandler db = new DataBaseHandler(this);

        System.out.println("Inserting ..");
        db.addContact(new Recipe("a1", "a)","a", "a", "a"));
        db.addContact(new Recipe("3", "434", "s", "asd", "sdsdkadasdas"));

        Recipe d =  db.getRecipe("a)");
        Toast.makeText(MainActivity.this, d.getName() + d.getType(), Toast.LENGTH_LONG).show();

        System.out.println("Reading all contacts..");
        List<Recipe> recipes = db.getAllRecipes();
        for (Recipe cn : recipes) {
            Toast.makeText(MainActivity.this, cn.getName() + cn.getType() + cn.getIngredients() + cn.getCooking(), Toast.LENGTH_LONG).show();;
        }

        db.deleteAll();

      /*  Button q = (Button) findViewById(R.id.soup) ;
        Button q1 = (Button) findViewById(R.id.second) ;
        Button q2 = (Button) findViewById(R.id.salats) ;

        View.OnClickListener onClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()){
                    case R.id.soup :{
                        changeActivity("Супы");
                        Toast.makeText(MainActivity.this, getString(R.string.action_item3), Toast.LENGTH_LONG).show();
                    }
                    break;
                    case R.id.second:{
                        changeActivity("Вторые блюда");
                    }
                    break;

                    case R.id.salats:{
                        changeActivity("Салаты");
                    }
                    break;
                }
            }
        };*/
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

    public void onClick(View view){
        switch (view.getId()){
            case R.id.soup :{
                changeActivity("Супы");
            }
            break;
            case R.id.second:{
                changeActivity("Вторые блюда");
            }
            break;
            case R.id.salats:{
                changeActivity("Салаты");
            }
            break;
            case R.id.fish:{
                changeActivity("Рыбные блюда");
            }
            break;
            case R.id.vegitables: {
                changeActivity("Блюда из овощей");
            }
            break;
            case R.id.salting: {
                changeActivity("Соленья, маринады");
            }
            break;
            case R.id.desert: {
                changeActivity("Десерты");
            }
            break;
            case R.id.little_cakes: {
                changeActivity("Запеканки");
            }
            break;
            case R.id.cakes: {
                changeActivity("Изделия из теста");
            }
            break;
        }
    }

    public void changeActivity(String type){
        Intent intent = new Intent(MainActivity.this, ShowRecipeActivity.class);
        intent.putExtra("type", type);
        //intent.putExtra("lastname", lastName.getText().toString());
        startActivity(intent);
      //  ShowRecipeActivity s = new ShowRecipeActivity();
     //   s.setTitle("sjdhsksfsfks");
    }
}