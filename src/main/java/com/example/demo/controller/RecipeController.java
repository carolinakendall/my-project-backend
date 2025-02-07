package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RecipeDto;
import com.example.demo.service.RecipeService;


@RequestMapping("/api/recipes")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

@Autowired
private RecipeService recipeService;

    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeService.createRecipe(recipeDto);
    }

    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

}
