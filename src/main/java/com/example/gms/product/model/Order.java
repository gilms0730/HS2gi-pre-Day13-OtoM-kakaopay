package com.example.gms.product.model;

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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="User_id")
    User user;

    String impUid;

    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProductList= new ArrayList<>();
}
