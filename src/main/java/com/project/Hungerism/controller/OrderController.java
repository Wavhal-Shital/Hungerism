package com.project.Hungerism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hungerism.model.CartItem;
import com.project.Hungerism.model.Order;
import com.project.Hungerism.model.User;
import com.project.Hungerism.request.AddCartItemRequest;
import com.project.Hungerism.request.OrderRequest;
import com.project.Hungerism.service.OrderService;
import com.project.Hungerism.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
 
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PutMapping("/orders")
    public ResponseEntity<Order>createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt)throws Exception{

        User user=userService.findUserByJwtToken(jwt);
        Order order= orderService.createOrder(req, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")

    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt)throws Exception{

        User user=userService.findUserByJwtToken(jwt);
       List<Order> order= orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    

}
