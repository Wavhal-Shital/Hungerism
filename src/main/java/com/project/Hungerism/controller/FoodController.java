package com.project.Hungerism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Hungerism.model.Food;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.model.User;
import com.project.Hungerism.request.CreateFoodRequest;
import com.project.Hungerism.service.FoodService;
import com.project.Hungerism.service.RestaurantService;
import com.project.Hungerism.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

     @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                            @RequestHeader ("Authorization") String jwt)throws Exception{
                User user= userService.findUserByJwtToken(jwt);

               List<Food> food= foodService.searchFood(name);


                return  new ResponseEntity<>(food,HttpStatus.CREATED);
                                                
     }

     @GetMapping("/restaurant/{restaurantId}")
     public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam boolean nonveg,
                                                        @RequestParam(required = false) String food_category,
                                                        @PathVariable Long restaurantId,
                                             @RequestHeader ("Authorization") String jwt)throws Exception{
                 User user= userService.findUserByJwtToken(jwt);
 
                List<Food> food= foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);
 
 
                 return  new ResponseEntity<>(food,HttpStatus.OK);
                                                 
      }
 

}
