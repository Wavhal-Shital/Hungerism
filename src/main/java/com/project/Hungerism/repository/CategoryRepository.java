package com.project.Hungerism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hungerism.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

    public List<Category> findByRestaurantId(Long id);


}
