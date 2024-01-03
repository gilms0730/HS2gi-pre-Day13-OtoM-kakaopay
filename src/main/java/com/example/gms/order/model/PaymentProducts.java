package com.example.gms.order.model;

import com.example.gms.product.model.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class PaymentProducts {
    List<Product> products;
}
