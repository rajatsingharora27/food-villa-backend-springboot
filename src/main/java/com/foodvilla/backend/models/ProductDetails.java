package com.foodvilla.backend.models;

public class ProductDetails {
//    "description": "string can be a para try to convert to html format",
//    public String description;

    public String storageAndConsumption;

    public String ingredients;

    public String allergens;

    public String getStorageAndConsumption() {
        return storageAndConsumption;
    }

    public void setStorageAndConsumption(String storageAndConsumption) {
        this.storageAndConsumption = storageAndConsumption;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }
}
