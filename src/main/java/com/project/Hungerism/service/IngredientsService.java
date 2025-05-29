package com.project.Hungerism.service;

import java.util.List;

import com.project.Hungerism.model.IngredientCategory;
import com.project.Hungerism.model.IngredientsItem;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name, Long RestaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);

    public IngredientsItem updateStock(Long id) throws Exception;

}
