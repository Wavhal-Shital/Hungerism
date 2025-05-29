package com.project.Hungerism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hungerism.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{

   
}
