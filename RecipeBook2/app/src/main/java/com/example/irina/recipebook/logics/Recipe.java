package com.example.irina.recipebook.logics;

import com.example.irina.recipebook.db.ShoppingListContract;

/**
 * Created by Irina on 27.11.2016.
 */

public class Recipe {
    private int id;
    private String type;
    private String name;
    private String cooking;
    private String ingredients;
    private String image;

    public Recipe() {
        id = 0;
        type = null;
        name = null;
        cooking = null;
        ingredients = null;
        image = null;
    }

    public Recipe(int id, String type, String name, String cooking, String ingredients, String image) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.cooking = cooking;
        this.ingredients = ingredients;
        this.image = image;
    }

    public Recipe(String type, String name,String ingredients, String cooking,  String image) {
        this.type = type;
        this.name = name;
        this.cooking = cooking;
        this.ingredients = ingredients;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


