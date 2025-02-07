package com.example.demo.service;

import com.example.demo.dto.IngredientDto;
import com.example.demo.dto.RecipeDto;
import com.example.demo.entity.Ingredient;
import com.example.demo.entity.Recipe;

import jakarta.transaction.Transactional;

import com.example.demo.dao.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    /*
     * Creates a new recipe
     */
    public RecipeDto createRecipe(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());

        List<Ingredient> ingredientList = dto.getIngredients().stream().map(ingredient ->
            new Ingredient(ingredient.getName(), ingredient.getQuantity(), recipe)
        ).collect(Collectors.toList());

        recipe.setIngredients(ingredientList);
        Recipe savedRecipe = recipeRepository.save(recipe);
        //TODO create mapper
        return new RecipeDto(savedRecipe.getName(), savedRecipe.getIngredients().stream()
                .map(ingredient -> new IngredientDto(ingredient.getName(), ingredient.getQuantity()))
                .collect(Collectors.toList()));
    }


    /*
     * Retrieves all recipes
     */
    @Transactional
    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAllWithIngredients();

        List<RecipeDto> listRecipesDto = new ArrayList<>();

        for(Recipe recipe : recipes) {
        RecipeDto newRecipe = new RecipeDto();
        newRecipe.setName(recipe.getName());

        List<IngredientDto> listIngredientDto = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setName(ingredient.getName());
            ingredientDto.setQuantity(ingredient.getQuantity());
            listIngredientDto.add(ingredientDto);
        }
        newRecipe.setIngredients(listIngredientDto);

        listRecipesDto.add(newRecipe);
        }

        return listRecipesDto;
    }
}
