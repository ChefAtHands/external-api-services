package com.chefathands.recipesearch.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Recipes", schema = "dbo")
public class RecipeCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecipeID")
    private Long id;

    @Column(name = "spoonacular_id", unique = true, nullable = true)
    private Long spoonacularId;

    @Column(name = "Title", nullable = true)
    private String title;

    @Column(name = "Instructions", columnDefinition = "NVARCHAR(MAX)", nullable = true)
    private String recipeData;

    @Column(name = "Category", nullable = true)
    private String category;

    @Column(name = "cached_at", nullable = true)  // Must be nullable
    private LocalDateTime cachedAt;

    public RecipeCache() {}

    public RecipeCache(Long spoonacularId, String recipeData, LocalDateTime cachedAt) {
        this.spoonacularId = spoonacularId;
        this.recipeData = recipeData;
        this.cachedAt = cachedAt;
    }

    // ...existing getters and setters...
    
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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