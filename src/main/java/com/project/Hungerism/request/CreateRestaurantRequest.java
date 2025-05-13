package com.project.Hungerism.request;

import java.util.List;

import com.project.Hungerism.model.Address;
import com.project.Hungerism.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

    

}
