package com.project.Hungerism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Hungerism.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

    

}
