package com.chefathands.recipesearch.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chefathands.recipesearch.client.SpoonacularClient;
import com.chefathands.recipesearch.dto.RecipeSearchRequest;
import com.chefathands.recipesearch.dto.RecipeSearchResponse;
import com.chefathands.recipesearch.model.Recipe;
import com.chefathands.recipesearch.model.RecipeCache;
import com.chefathands.recipesearch.repository.RecipeCacheRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipeSearchService {

    private static final Logger logger = LoggerFactory.getLogger(RecipeSearchService.class);
    private static final int CACHE_EXPIRY_HOURS = 24;

    private final SpoonacularClient spoonacularClient;
    private final RecipeCacheRepository recipeCacheRepository;
    private final ObjectMapper objectMapper;

    public RecipeSearchService(SpoonacularClient spoonacularClient, 
                              RecipeCacheRepository recipeCacheRepository,
                              ObjectMapper objectMapper) {
        this.spoonacularClient = spoonacularClient;
        this.recipeCacheRepository = recipeCacheRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Search recipes by ingredients with caching
     */
    public RecipeSearchResponse searchRecipes(RecipeSearchRequest request) {
        logger.info("Searching recipes with ingredients: {}", request.getIngredients());

        try {
            // Call Spoonacular API
            JsonNode response = spoonacularClient.searchRecipesByIngredients(request);
            
            // Parse response
            List<Recipe> recipes = new ArrayList<>();
            JsonNode resultsNode = response.get("results");
            
            if (resultsNode != null && resultsNode.isArray()) {
                for (JsonNode node : resultsNode) {
                    try {
                        logger.debug("Processing recipe node: {}", node.toString());
                        Recipe recipe = objectMapper.treeToValue(node, Recipe.class);
                        recipes.add(recipe);
                        
                        // Cache the recipe node (not the Recipe object)
                        cacheRecipe(node);
                    } catch (Exception e) {
                        logger.error("Error parsing recipe node", e);
                    }
                }
            }

            Integer totalResults = response.has("totalResults") ? response.get("totalResults").asInt() : recipes.size();
            Integer offset = response.has("offset") ? response.get("offset").asInt() : 0;
            Integer number = response.has("number") ? response.get("number").asInt() : recipes.size();

            logger.info("Found {} recipes out of {} total results", recipes.size(), totalResults);

            return new RecipeSearchResponse(recipes, totalResults, offset, number);

        } catch (Exception e) {
            logger.error("Error searching recipes", e);
            throw new RuntimeException("Failed to search recipes: " + e.getMessage());
        }
    }

    /**
     * Get recipe details by ID (check cache first)
     */
    public Recipe getRecipeById(Long recipeId) {
        logger.info("Fetching recipe with ID: {}", recipeId);

        // Check cache first
        Optional<RecipeCache> cachedRecipe = recipeCacheRepository.findBySpoonacularIdAndCachedAtAfter(
                recipeId, 
                LocalDateTime.now().minusHours(CACHE_EXPIRY_HOURS)
        );

        if (cachedRecipe.isPresent()) {
            logger.info("Recipe found in cache: {}", recipeId);
            try {
                return objectMapper.readValue(cachedRecipe.get().getRecipeData(), Recipe.class);
            } catch (Exception e) {
                logger.error("Error parsing cached recipe", e);
            }
        }

        // Fetch from API if not in cache
        Recipe recipe = spoonacularClient.getRecipeDetails(recipeId);
        
        // Convert Recipe to JsonNode for caching
        try {
            JsonNode recipeNode = objectMapper.valueToTree(recipe);
            cacheRecipe(recipeNode);
        } catch (Exception e) {
            logger.error("Error converting recipe to JSON for caching", e);
        }
        
        return recipe;
    }

    /**
     * Cache recipe data
     */
    private void cacheRecipe(JsonNode recipeNode) {
        try {
            Long spoonacularId = recipeNode.get("id").asLong();
            String recipeJson = recipeNode.toString();
            
            // Extract title from the JSON
            String title = recipeNode.has("title") ? recipeNode.get("title").asText() : "Unknown Recipe";
            
            RecipeCache cache = new RecipeCache(spoonacularId, recipeJson, LocalDateTime.now());
            cache.setTitle(title);  // Set the title before saving
            
            recipeCacheRepository.save(cache);
            logger.info("Successfully cached recipe: {} (ID: {})", title, spoonacularId);
        } catch (Exception e) {
            logger.error("Error caching recipe: {}", recipeNode.get("id").asLong(), e);
        }
    }   
}