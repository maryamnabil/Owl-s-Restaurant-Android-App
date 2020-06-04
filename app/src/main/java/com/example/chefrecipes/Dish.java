package com.example.chefrecipes;

public class Dish {
    String title;
    String price;
    String ingredients;
    String imageUrl;
public Dish(){

}

    public  Dish(String title,String price,String ingredients,String imageUrl){
        this.ingredients = ingredients;
        this.price = price;
        this.title = title;
        this.imageUrl = imageUrl;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
