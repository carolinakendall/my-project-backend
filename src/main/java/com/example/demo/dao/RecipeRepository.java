package com.example.demo.dao;

import com.example.demo.entity.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
      
    /*
     * Retrieves the existings recipes forcing the loading of ingredients
     */
    @Query("SELECT recipe FROM Recipe recipe LEFT JOIN FETCH recipe.ingredients")
    List<Recipe> findAllWithIngredients();

}
