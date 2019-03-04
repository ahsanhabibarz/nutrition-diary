package com.example.nutritiondiary;

public class FoodNutritionList {

    String name,weight,quantity,calories,fat,carbs,protein,measurementunit;


    public FoodNutritionList() {
    }


    public FoodNutritionList(String name, String weight, String quantity, String calories, String fat, String carbs, String protein, String measurementunit) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.measurementunit = measurementunit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getMeasurementunit() {
        return measurementunit;
    }

    public void setMeasurementunit(String measurementunit) {
        this.measurementunit = measurementunit;
    }
}
