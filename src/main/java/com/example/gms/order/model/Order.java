package com.example.gms.order.model;

import com.example.gms.user.model.User;
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
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="User_id")
    User user;

    String impUid;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProductList= new ArrayList<>();
}
