package com.example.E_CommerceProject.Controller;

import com.example.E_CommerceProject.DTO.OrdersDTO;
import com.example.E_CommerceProject.Entity.OrderRequest;
import com.example.E_CommerceProject.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/place/{userId}")
    public OrdersDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest) {
        return ordersService.placeOrder(userId, orderRequest.getProductQuantities(), orderRequest.getTotalAmount());
    }

    @GetMapping("/all-orders")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrdersDTO> getOrderByUser(@PathVariable Long userId) {
        return ordersService.getOrderByUser(userId);
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable Long id){
        ordersService.cancelOrder(id);
    }
}
