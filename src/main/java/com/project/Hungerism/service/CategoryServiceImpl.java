package com.project.Hungerism.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Hungerism.model.Category;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category createCategory(String name, Long userId) {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category= new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {

        Restaurant restaurant= restaurantService.getRestaurantByUserId(id);

        return categoryRepository.findByRestaurantId(restaurant.getId());
        
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
    
        Optional<Category> optionalCategory= categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("category not found");
        }

        return optionalCategory.get();
    }

}
