package com.chefathands.recipesearch.repository;

import com.chefathands.recipesearch.model.RecipeCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RecipeCacheRepository extends JpaRepository<RecipeCache, Long> {
    
    Optional<RecipeCache> findBySpoonacularId(Long spoonacularId);
    
    Optional<RecipeCache> findBySpoonacularIdAndCachedAtAfter(Long spoonacularId, LocalDateTime cachedAfter);
}