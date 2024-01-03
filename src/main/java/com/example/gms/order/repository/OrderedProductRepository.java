package com.example.gms.order.repository;

import com.example.gms.order.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long>{

}
