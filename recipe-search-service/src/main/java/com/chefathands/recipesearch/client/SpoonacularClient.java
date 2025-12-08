package com.chefathands.recipesearch.client;

import com.chefathands.recipesearch.dto.RecipeSearchRequest;
import com.chefathands.recipesearch.model.Recipe;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SpoonacularClient {

    private static final Logger logger = LoggerFactory.getLogger(SpoonacularClient.class);
    private static final String BASE_URL = "https://api.spoonacular.com";

    @Value("${spoonacular.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public SpoonacularClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Search recipes by ingredients and nutritional requirements
     */
    public JsonNode searchRecipesByIngredients(RecipeSearchRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BASE_URL + "/recipes/complexSearch")
                .queryParam("apiKey", apiKey)
                .queryParam("includeIngredients", String.join(",", request.getIngredients()))
                .queryParam("number", request.getNumber())
                .queryParam("offset", request.getOffset())
                .queryParam("addRecipeInformation", true);  // ADD THIS LINE

        // Add nutritional parameters if provided
        if (request.getMinProtein() != null) {
            builder.queryParam("minProtein", request.getMinProtein());
        }
        if (request.getMaxProtein() != null) {
            builder.queryParam("maxProtein", request.getMaxProtein());
        }
        if (request.getMinCarbs() != null) {
            builder.queryParam("minCarbs", request.getMinCarbs());
        }
        if (request.getMaxCarbs() != null) {
            builder.queryParam("maxCarbs", request.getMaxCarbs());
        }
        if (request.getMinCalories() != null) {
            builder.queryParam("minCalories", request.getMinCalories());
        }
        if (request.getMaxCalories() != null) {
            builder.queryParam("maxCalories", request.getMaxCalories());
        }
        if (request.getMinFat() != null) {
            builder.queryParam("minFat", request.getMinFat());
        }
        if (request.getMaxFat() != null) {
            builder.queryParam("maxFat", request.getMaxFat());
        }
        if (request.getType() != null) {
            builder.queryParam("type", request.getType());
        }
        if (request.getDiet() != null) {
            builder.queryParam("diet", request.getDiet());
        }

        String url = builder.toUriString();
        logger.info("Calling Spoonacular API: {}", url);

        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        logger.debug("Spoonacular response: {}", response);
        
        return response;
    }

    /**
     * Get detailed recipe information by ID
     */
    public Recipe getRecipeDetails(Long recipeId) {
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL + "/recipes/" + recipeId + "/information")
                .queryParam("apiKey", apiKey)
                .queryParam("includeNutrition", true)
                .toUriString();

        logger.info("Fetching recipe details: {}", url);

        Recipe recipe = restTemplate.getForObject(url, Recipe.class);
        logger.debug("Recipe details: {}", recipe);
        
        return recipe;
    }
}