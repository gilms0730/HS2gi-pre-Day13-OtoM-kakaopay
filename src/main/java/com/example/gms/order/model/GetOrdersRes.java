package com.example.gms.order.model;

import com.example.gms.product.model.dto.ProductReadRes;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetOrdersRes {
    Long id;
    String userName;
    List<ProductReadRes> products;
}
