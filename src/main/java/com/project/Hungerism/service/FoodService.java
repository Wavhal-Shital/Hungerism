package com.project.Hungerism.service;

import java.util.List;
import java.util.Locale.Category;

import com.project.Hungerism.model.Food;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.request.CreateFoodRequest;

public interface FoodService {

public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

void deleteFood(Long foodId) throws Exception;

public List<Food> getRestaurantsFood(Long restaurantId,
                                     boolean isVegetarian, 
                                     boolean isNonveg, 
                                     boolean isSeasonal, 
                                     String foodCategory
);

public List<Food> searchFood(String keyword);

public Food findFoodById(Long foodId) throws Exception;

public Food updateAvailabilityStatus(Long foodId) throws Exception;




}
