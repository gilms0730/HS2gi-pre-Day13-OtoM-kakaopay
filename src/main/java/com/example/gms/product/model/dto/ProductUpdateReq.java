package com.example.gms.product.model.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductUpdateReq {
    Long id;
    String name;
    Integer price;
}
