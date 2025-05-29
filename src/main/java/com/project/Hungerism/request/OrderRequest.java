package com.project.Hungerism.request;

import com.project.Hungerism.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;

    private Addresss deliveryAddress;

}
