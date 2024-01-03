package com.example.gms.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductReadRes {
    Long id;
    String name;
    Integer price;
}
