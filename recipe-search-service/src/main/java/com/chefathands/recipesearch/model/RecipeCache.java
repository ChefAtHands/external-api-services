package com.chefathands.recipesearch.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe_cache")
public class RecipeCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spoonacular_id", unique = true, nullable = false)
    private Long spoonacularId;

    @Column(name = "recipe_data", columnDefinition = "TEXT", nullable = false)
    private String recipeData;

    @Column(name = "cached_at", nullable = false)
    private LocalDateTime cachedAt;

    public RecipeCache() {}

    public RecipeCache(Long spoonacularId, String recipeData, LocalDateTime cachedAt) {
        this.spoonacularId = spoonacularId;
        this.recipeData = recipeData;
        this.cachedAt = cachedAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpoonacularId() {
        return spoonacularId;
    }

    public void setSpoonacularId(Long spoonacularId) {
        this.spoonacularId = spoonacularId;
    }

    public String getRecipeData() {
        return recipeData;
    }

    public void setRecipeData(String recipeData) {
        this.recipeData = recipeData;
    }

    public LocalDateTime getCachedAt() {
        return cachedAt;
    }

    public void setCachedAt(LocalDateTime cachedAt) {
        this.cachedAt = cachedAt;
    }
}