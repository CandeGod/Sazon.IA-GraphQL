package com.proyecto.SazonIA.spoonacularApi;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRecipe {
    
    private Integer id;

    private String title;

    private String image;

    private Integer readyInMinutes;

    private Integer cookingMinutes;

    private Integer preparationMinutes;

    private String sourceName;

    private String sourceUrl;

    private String spoonacularSourceUrl;

    private Integer servings;

    private List<ApiIngredientsRecipe> extendedIngredients;

    private String summary;

    private ApiWinePairingRecipe winePairing;

    

    public ApiRecipe(Integer id, String title, String image, Integer readyInMinutes, Integer cookingMinutes,
            Integer preparationMinutes, String sourceName, String sourceUrl, String spoonacularSourceUrl,
            Integer servings, List<ApiIngredientsRecipe> extendedIngredients, String summary,
            ApiWinePairingRecipe winePairing) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.readyInMinutes = readyInMinutes;
        this.cookingMinutes = cookingMinutes;
        this.preparationMinutes = preparationMinutes;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
        this.servings = servings;
        this.extendedIngredients = extendedIngredients;
        this.summary = summary;
        this.winePairing = winePairing;
    }

    
    
}








