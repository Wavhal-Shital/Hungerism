package com.project.Hungerism.service;

import java.util.List;

import com.project.Hungerism.model.Category;

public interface CategoryService {

    public Category createCategory(String name, Long userId);

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
