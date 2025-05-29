package com.project.Hungerism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hungerism.model.Cart;
import com.project.Hungerism.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // public Cart findByCustomerId(Long userId);

    


}
