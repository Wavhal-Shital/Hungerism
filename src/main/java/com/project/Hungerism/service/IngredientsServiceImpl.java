package com.project.Hungerism.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Hungerism.model.IngredientCategory;
import com.project.Hungerism.model.IngredientsItem;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.repository.IngredientCategoryRepository;
import com.project.Hungerism.repository.IngredientItemRepository;

@Service
public class IngredientsServiceImpl implements IngredientsService{

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
       
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category= new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);


        return ingredientCategoryRepository.save(category);

        
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
       
        Optional<IngredientCategory> opt=ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)throws Exception{
           
      Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
      IngredientCategory category= findIngredientCategoryById(categoryId);

      IngredientsItem item= new IngredientsItem();
      item.setName(ingredientName);
      item.setRestaurant(restaurant);
      item.setCategory(category);

      IngredientsItem ingredient=ingredientItemRepository.save(item);
      category.getIngredients().add(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {


        return ingredientItemRepository.findByRestaurantId(restaurantId);
        
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> optionalIngredientsItem= ingredientItemRepository.findById(id);

        if(optionalIngredientsItem.isEmpty()){
            throw new Exception("ingredient not found");
        }
        IngredientsItem ingredientsItem=optionalIngredientsItem.get();
        IngredientsItem.setInStock(!ingredientsItem.isInStoke());

        return ingredientItemRepository.save(ingredientsItem);
      
    }





}
