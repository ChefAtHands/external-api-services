package com.chefathands.recipesearch.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class RecipeSearchRequest {

    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<String> ingredients;

    @Min(value = 1, message = "Number must be at least 1")
    private Integer number = 10;

    @Min(value = 0, message = "Offset must be non-negative")
    private Integer offset = 0;

    // Nutritional filters
    private Integer minProtein;
    private Integer maxProtein;
    private Integer minCarbs;
    private Integer maxCarbs;
    private Integer minCalories;
    private Integer maxCalories;
    private Integer minFat;
    private Integer maxFat;

    // Recipe type (e.g., "main course", "dessert", "appetizer")
    private String type;
    
    // Diet type (e.g., "vegetarian", "vegan", "gluten free")
    private String diet;

    // Getters and Setters
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getMinProtein() {
        return minProtein;
    }

    public void setMinProtein(Integer minProtein) {
        this.minProtein = minProtein;
    }

    public Integer getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(Integer maxProtein) {
        this.maxProtein = maxProtein;
    }

    public Integer getMinCarbs() {
        return minCarbs;
    }

    public void setMinCarbs(Integer minCarbs) {
        this.minCarbs = minCarbs;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(Integer minCalories) {
        this.minCalories = minCalories;
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMinFat() {
        return minFat;
    }

    public void setMinFat(Integer minFat) {
        this.minFat = minFat;
    }

    public Integer getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(Integer maxFat) {
        this.maxFat = maxFat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}