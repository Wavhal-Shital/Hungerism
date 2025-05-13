package com.project.Hungerism.controller;

import java.util.List;

import org.hibernate.annotations.processing.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hungerism.dto.RestaurantDto;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.model.User;
import com.project.Hungerism.request.CreateRestaurantRequest;
import com.project.Hungerism.service.RestaurantService;
import com.project.Hungerism.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

     @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

   


   

    @PostMapping("")    
    public ResponseEntity<Restaurant> createRestaurant(
    @RequestBody CreateRestaurantRequest req,
    @RequestHeader("Authorization") String jwt 
    )throws Exception {
       User user= userService.findUserByJwtToken(jwt);

       Restaurant restaurant=restaurantService.createRestaurant(req, user);
        return new  ResponseEntity<>(restaurant, HttpStatus.OK);

    }


    @GetMapping("")    
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
    @RequestHeader("Authorization") String jwt 
    )throws Exception {
       User user= userService.findUserByJwtToken(jwt);

       List<Restaurant> restaurant=restaurantService.getAllRestaurant();
        return new  ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}")    
    public ResponseEntity <Restaurant> findRestaurantById(
    @RequestHeader("Authorization") String jwt,
    @PathVariable Long id
    )throws Exception {
       User user= userService.findUserByJwtToken(jwt);

       Restaurant restaurant=restaurantService.findRestaurantById(id);
        return new  ResponseEntity<>(restaurant, HttpStatus.OK);

    }
    @PutMapping("/{id}/add-favorites")    
    public ResponseEntity <RestaurantDto> addToFavorites(
    @RequestHeader("Authorization") String jwt,
    @PathVariable Long id
    )throws Exception {
       User user= userService.findUserByJwtToken(jwt);

       RestaurantDto restaurant=restaurantService.addToFavorites(id, user);

        return new  ResponseEntity<>(restaurant, HttpStatus.OK);

    }


}
