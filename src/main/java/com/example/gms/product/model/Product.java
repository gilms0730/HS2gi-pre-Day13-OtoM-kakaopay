package com.example.gms.product.model;

import com.example.gms.order.model.OrderedProduct;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProductList = new ArrayList<>();
}
