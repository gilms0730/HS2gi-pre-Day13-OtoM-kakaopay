package com.example.gms.order.model;

import com.example.gms.product.model.Product;
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
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="Order_id")
    Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Product_id")
    Product product;

}
