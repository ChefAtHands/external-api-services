package com.chefathands.recipesearch.controller;

import com.chefathands.recipesearch.dto.RecipeSearchRequest;
import com.chefathands.recipesearch.dto.RecipeSearchResponse;
import com.chefathands.recipesearch.model.Recipe;
import com.chefathands.recipesearch.service.RecipeSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeSearchController {

    private final RecipeSearchService recipeSearchService;

    public RecipeSearchController(RecipeSearchService recipeSearchService) {
        this.recipeSearchService = recipeSearchService;
    }

    /**
     * POST /api/recipes/search
     * Search recipes by ingredients
     */
    @PostMapping("/search")
    public ResponseEntity<RecipeSearchResponse> searchRecipes(@Valid @RequestBody RecipeSearchRequest request) {
        RecipeSearchResponse response = recipeSearchService.searchRecipes(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/recipes/{id}
     * Get recipe details by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeSearchService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }
}