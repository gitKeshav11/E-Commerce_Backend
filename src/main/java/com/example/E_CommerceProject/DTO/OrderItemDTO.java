package com.example.E_CommerceProject.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO
{
    //use: only for fetching product details
    private String productName;
    private double productPrice;
    private int quantity;

    public OrderItemDTO(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
