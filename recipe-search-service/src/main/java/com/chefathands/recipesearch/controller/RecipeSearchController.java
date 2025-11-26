package com.chefathands.recipesearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeSearchController {
    @GetMapping("/api/recipes/search")
    public String searchRecipes(@RequestParam String query) {
        // Implement search logic here
        return "Searching for recipes with query: " + query;
    }
}