package com.project.Hungerism.service;

import java.util.List;

import com.project.Hungerism.model.Order;
import com.project.Hungerism.model.User;
import com.project.Hungerism.request.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user);

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId)throws Exception;

    public List<Order>getUserOrder(Long userId) throws Exception;

    public List<Order>getRestaurantOrder(Long restaurantId, String orderStatus)throws Exception;

    public Order findOrderById(Long orderId)throws Exception;



}
