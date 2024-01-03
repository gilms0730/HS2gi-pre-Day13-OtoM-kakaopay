package com.example.gms.product.model;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;
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
    Integer id;
    String name;
    Integer price;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orderedProductList = new ArrayList<>();
}
