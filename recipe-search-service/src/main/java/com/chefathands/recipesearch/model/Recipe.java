package com.chefathands.recipesearch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
    private Long id;
    private String title;
    private String image;
    private Integer readyInMinutes;
    private Integer servings;
    private String sourceUrl;
    
    // Nutritional information
    private Nutrition nutrition;
    
    // Extended info (from detail endpoint)
    private String summary;
    private List<ExtendedIngredient> extendedIngredients;
    private List<AnalyzedInstruction> analyzedInstructions;

    public Recipe() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    // Inner classes for nested data structures
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutrition {
        private List<Nutrient> nutrients;

        public List<Nutrient> getNutrients() {
            return nutrients;
        }

        public void setNutrients(List<Nutrient> nutrients) {
            this.nutrients = nutrients;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutrient {
        private String name;
        private Double amount;
        private String unit;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtendedIngredient {
        private Long id;
        private String name;
        private Double amount;
        private String unit;
        private String original;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AnalyzedInstruction {
        private String name;
        private List<Step> steps;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Step {
        private Integer number;
        private String step;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}