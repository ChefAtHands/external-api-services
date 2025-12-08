package com.chefathands.recipesearch.dto;

import com.chefathands.recipesearch.model.Recipe;
import java.util.List;

public class RecipeSearchResponse {
    private List<Recipe> results;
    private Integer totalResults;
    private Integer offset;
    private Integer number;

    public RecipeSearchResponse() {}

    public RecipeSearchResponse(List<Recipe> results, Integer totalResults, Integer offset, Integer number) {
        this.results = results;
        this.totalResults = totalResults;
        this.offset = offset;
        this.number = number;
    }

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}