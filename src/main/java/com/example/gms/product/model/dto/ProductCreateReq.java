package com.example.gms.product.model.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductCreateReq {
    String name;
    Integer price;
}
