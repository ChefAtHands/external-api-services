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
     * Search recipes using JSON body (used by recommendation-service)
     */
    @PostMapping("/search")
    public ResponseEntity<RecipeSearchResponse> searchRecipes(
            @Valid @RequestBody RecipeSearchRequest request
    ) {
        RecipeSearchResponse response = recipeSearchService.searchRecipes(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/recipes/search
     * Search recipes using query parameters (optional but very useful)
     */
    @GetMapping("/search")
    public ResponseEntity<RecipeSearchResponse> searchRecipesGet(
            @RequestParam List<String> ingredients,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minProtein,
            @RequestParam(required = false) Integer maxProtein,
            @RequestParam(required = false) Integer minCarbs,
            @RequestParam(required = false) Integer maxCarbs,
            @RequestParam(required = false) Integer minCalories,
            @RequestParam(required = false) Integer maxCalories,
            @RequestParam(required = false) Integer minFat,
            @RequestParam(required = false) Integer maxFat,
            @RequestParam(defaultValue = "10") Integer number,
            @RequestParam(defaultValue = "0") Integer offset
    ) {
        RecipeSearchRequest req = new RecipeSearchRequest();

        req.setIngredients(ingredients);
        req.setDiet(diet);
        req.setType(type);

        req.setMinProtein(minProtein);
        req.setMaxProtein(maxProtein);
        req.setMinCarbs(minCarbs);
        req.setMaxCarbs(maxCarbs);
        req.setMinCalories(minCalories);
        req.setMaxCalories(maxCalories);
        req.setMinFat(minFat);
        req.setMaxFat(maxFat);

        req.setNumber(number);
        req.setOffset(offset);

        RecipeSearchResponse response = recipeSearchService.searchRecipes(req);
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