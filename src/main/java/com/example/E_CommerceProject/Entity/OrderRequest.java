package com.example.E_CommerceProject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {
    private Map<Long,Integer> productQuantities;
    private double totalAmount;


}
