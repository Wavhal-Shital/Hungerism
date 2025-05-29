package com.project.Hungerism.service;

import com.project.Hungerism.model.Cart;
import com.project.Hungerism.model.CartItem;
import com.project.Hungerism.request.AddCartItemRequest;

public interface CartService {


    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long CardItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long CardItemId, String jwt) throws Exception;
    

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
