package com.project.Hungerism.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Hungerism.dto.RestaurantDto;
import com.project.Hungerism.model.Address;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.model.User;
import com.project.Hungerism.repository.AddressRepository;
import com.project.Hungerism.repository.RestaurantRepository;
import com.project.Hungerism.repository.UserRepository;
import com.project.Hungerism.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        RestaurantDto dto= new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

     
        boolean isFavorited= false;
        List<RestaurantDto> favorites= user.getFavorites();
        for(RestaurantDto favorite: favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorited=true;
                break;
            }
        }

        if(isFavorited){
            favorites.removeIf(favorite ->favorite.getId().equals(restaurantId));
        }else{
            favorites.add(dto);
        }


        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address= addressRepository.save(req.getAddress());

        Restaurant restaurant= new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactinformation(req.getContactInformation());
        restaurant.setDescription(req.getDescription());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);

        
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
       
        Optional<Restaurant> opt= restaurantRepository.findById(id);

        if(opt.isEmpty()){

          throw new Exception ("restaurant not found with id"+id);

        }
        return opt.get();
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
     
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant= restaurantRepository.findByOwnerId(userId);

        if(restaurant==null){
            throw new Exception("restaurant not found with owner id"+userId);
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
       
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
      
        Restaurant restaurant= findRestaurantById(restaurantId);

        if (restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }
  


    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
      
        Restaurant restaurant= findRestaurantById(id);

        restaurant.setOpen(!restaurant.isOpen());


        return restaurantRepository.save(restaurant);
    }


}
