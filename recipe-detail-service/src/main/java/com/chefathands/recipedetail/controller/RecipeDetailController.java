package com.chefathands.recipedetail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeDetailController {
    @GetMapping("/api/recipes/{id}")
    public String getRecipeDetail(@PathVariable String id) {
        // Implement detail retrieval logic here
        return "Details for recipe ID: " + id;
    }
}