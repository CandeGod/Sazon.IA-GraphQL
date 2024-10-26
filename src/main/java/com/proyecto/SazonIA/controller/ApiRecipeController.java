package com.proyecto.SazonIA.controller;

import com.proyecto.SazonIA.service.ApiRecipeService;
import com.proyecto.SazonIA.spoonacularApi.ApiRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ApiRecipeController {

    private final ApiRecipeService apiRecipeService;

    @Autowired
    public ApiRecipeController(ApiRecipeService apiRecipeService) {
        this.apiRecipeService = apiRecipeService;
    }

    @QueryMapping
    public ApiRecipe getRandomRecipe() {
        return apiRecipeService.getRandomRecipe();
    }
}
