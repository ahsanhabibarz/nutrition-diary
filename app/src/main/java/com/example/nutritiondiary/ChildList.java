package com.example.nutritiondiary;

public class ChildList extends PostID {

    String name,imagepath;

    double fatpercentage,goalweight,heightft,heightinch,idealweight,age,weight,bmi,calories;

    public ChildList(){}


    public ChildList(String name, String imagepath, double fatpercentage, double goalweight, double heightft, double heightinch, double idealweight, double age, double weight, double bmi, double calories) {
        this.name = name;
        this.imagepath = imagepath;
        this.fatpercentage = fatpercentage;
        this.goalweight = goalweight;
        this.heightft = heightft;
        this.heightinch = heightinch;
        this.idealweight = idealweight;
        this.age = age;
        this.weight = weight;
        this.bmi = bmi;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public double getFatpercentage() {
        return fatpercentage;
    }

    public void setFatpercentage(double fatpercentage) {
        this.fatpercentage = fatpercentage;
    }

    public double getGoalweight() {
        return goalweight;
    }

    public void setGoalweight(double goalweight) {
        this.goalweight = goalweight;
    }

    public double getHeightft() {
        return heightft;
    }

    public void setHeightft(double heightft) {
        this.heightft = heightft;
    }

    public double getHeightinch() {
        return heightinch;
    }

    public void setHeightinch(double heightinch) {
        this.heightinch = heightinch;
    }

    public double getIdealweight() {
        return idealweight;
    }

    public void setIdealweight(double idealweight) {
        this.idealweight = idealweight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
