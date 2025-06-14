package com.project.Hungerism.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Hungerism.model.Address;
import com.project.Hungerism.model.Cart;
import com.project.Hungerism.model.CartItem;
import com.project.Hungerism.model.Order;
import com.project.Hungerism.model.OrderItem;
import com.project.Hungerism.model.Restaurant;
import com.project.Hungerism.model.User;
import com.project.Hungerism.repository.AddressRepository;
import com.project.Hungerism.repository.OrderItemRepository;
import com.project.Hungerism.repository.OrderRepository;
import com.project.Hungerism.repository.RestaurantRepository;
import com.project.Hungerism.repository.UserRepository;
import com.project.Hungerism.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) {
       
        Address shippedAddress=order.getDeliveryAddress();

        Address savedAddress=addressRepository.save(shippedAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant= restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder=new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("pending");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart=cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems=new ArrayList<>();
        for(CartItem cartItem: cart.getItem()){
            OrderItem orderItem= new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            
            OrderItem savedOrderItem=orderItemRepository.save(orderItem);

            orderItems.add(savedOrderItem);
        }
     
        Long totalPrice= cartService.calculateCartTotals(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);


        Order savedOrder=orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);
    
        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order=findOrderById(orderId);
        if(orderStatus.equals("Out for delivery") || orderStatus.equals("delivered") || orderStatus.equals("Completed") || order.equals("pending")){
            order.setOrderStatus(orderStatus);

            return orderRepository.save(order);
        }
        throw new Exception("Please select valid order status");
       
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

        Order order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
      
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {

       return orderRepository.findByCustomerId(userId);

       
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        
        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders=orders.stream().filter(order-> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {

        Optional<Order>optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }
       
        return optionalOrder.get();
    }

}
