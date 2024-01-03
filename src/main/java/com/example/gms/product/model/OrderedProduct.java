package com.example.gms.product.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="Order_id")
    Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Product_id")
    Product product;

}
