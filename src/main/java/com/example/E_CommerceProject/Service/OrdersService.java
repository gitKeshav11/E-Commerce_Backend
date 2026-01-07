package com.example.E_CommerceProject.Service;

import com.example.E_CommerceProject.DTO.OrderItemDTO;
import com.example.E_CommerceProject.DTO.OrdersDTO;
import com.example.E_CommerceProject.Entity.OrderItems;
import com.example.E_CommerceProject.Entity.Orders;
import com.example.E_CommerceProject.Entity.Product;
import com.example.E_CommerceProject.Entity.User;
import com.example.E_CommerceProject.Repository.OrderRepo;
import com.example.E_CommerceProject.Repository.ProductRepo;
import com.example.E_CommerceProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    public OrdersDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
       Integer intUserID= Math.toIntExact(userId);
        User user = userRepo.findById(intUserID).orElseThrow(() -> new RuntimeException("User not found"));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setOrderDate(new Date());
        orders.setStatus("Pending");
        orders.setTotalAmount(totalAmount);

        List<OrderItems> orderItemsList = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product Not found"));


            OrderItems orderItems = new OrderItems();
            orderItems.setOrders(orders);
            orderItems.setProduct(product);
            orderItems.setQuantity(entry.getValue());
            orderItemsList.add(orderItems);

            orderItemDTOList.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));

        }
        orders.setOrderItemsList(orderItemsList);
        Orders saveOrder = orderRepo.save(orders);
        return new OrdersDTO(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getStatus(),saveOrder.getOrderDate(),orderItemDTOList);
    }

    public List<OrdersDTO> getAllOrders() {
List<Orders> orders = orderRepo.findAllOrderWithUsers();
return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrdersDTO convertToDTO(Orders orders){
     List<OrderItemDTO> orderItems = orders.getOrderItemsList().stream().map(item->new OrderItemDTO(item.getProduct().getName(),item.getProduct().getPrice(),item.getQuantity())).collect((Collectors.toList()));
     return new OrdersDTO(
             orders.getId(),
             orders.getTotalAmount(),
             orders.getStatus(),
             orders.getOrderDate(),
             orders.getUser()!=null ? orders.getUser().getName():"Unknown",
             orders.getUser()!=null ? orders.getUser().getEmail():"Unknown",
             orderItems
             );
}

    public List<OrdersDTO> getOrderByUser(Long userId) {
        Integer intUserID= Math.toIntExact(userId);

        Optional<User> userOP=userRepo.findById(intUserID);
        if(userOP.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = userOP.get();
        List<Orders> ordersList = orderRepo.findByUser(user);
        return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void cancelOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
