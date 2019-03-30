package com.example.nutritiondiary;

public class MealList extends  PostID {

    String name,unit,image;
    double calories,carbs,fat,fiber,protein,quantity,suger,weight;
    int time;

    public MealList(){}

    public MealList(String name, String unit, String image, double calories, double carbs, double fat, double fiber, double protein, double quantity, double suger, double weight, int time) {
        this.name = name;
        this.unit = unit;
        this.image = image;
        this.calories = calories;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
        this.protein = protein;
        this.quantity = quantity;
        this.suger = suger;
        this.weight = weight;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSuger() {
        return suger;
    }

    public void setSuger(double suger) {
        this.suger = suger;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
