package com.example.E_CommerceProject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrdersDTO {
    // iska use isliye kia, agar hame only order ki details chahiye to user ki detail bhi na aae
    private Long id;
    private double totalAmount;
    private String status;
    private Date orderDate;
    private String userName;
    private String email;
    private List<OrderItemDTO> orderItemDTO;// isliye liya kyuki order ki details ke sath order itmes bhi dikhe

    public OrdersDTO(Long id, double totalAmount, String status, Date orderDate, String userName, String email, List<OrderItemDTO> orderItemDTO) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.userName = userName;
        this.email = email;
        this.orderItemDTO = orderItemDTO;
    }

    public OrdersDTO(Long id, double totalAmount, String status, Date orderDate, List<OrderItemDTO> orderItemDTO) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.userName = userName;
        this.email = email;
        this.orderItemDTO = orderItemDTO;
    }

}
