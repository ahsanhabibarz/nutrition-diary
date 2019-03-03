package com.example.nutritiondiary;

public class ChildList extends PostID {

    String name,fatper,gweight,heightinch,heightft,idealweight,age,weight,bmi,calories,imagepath;

    public ChildList(){}

    public ChildList(String name, String fatper, String gweight, String heightinch, String heightft, String idealweight, String age, String weight, String bmi, String calories, String imagepath) {
        this.name = name;
        this.fatper = fatper;
        this.gweight = gweight;
        this.heightinch = heightinch;
        this.heightft = heightft;
        this.idealweight = idealweight;
        this.age = age;
        this.weight = weight;
        this.bmi = bmi;
        this.calories = calories;
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatper() {
        return fatper;
    }

    public void setFatper(String fatper) {
        this.fatper = fatper;
    }

    public String getGweight() {
        return gweight;
    }

    public void setGweight(String gweight) {
        this.gweight = gweight;
    }

    public String getHeightinch() {
        return heightinch;
    }

    public void setHeightinch(String heightinch) {
        this.heightinch = heightinch;
    }

    public String getHeightft() {
        return heightft;
    }

    public void setHeightft(String heightft) {
        this.heightft = heightft;
    }

    public String getIdealweight() {
        return idealweight;
    }

    public void setIdealweight(String idealweight) {
        this.idealweight = idealweight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
