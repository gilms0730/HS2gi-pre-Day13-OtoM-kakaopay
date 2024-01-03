package com.example.gms.product.service;
import com.example.gms.order.model.PaymentProducts;
import com.example.gms.product.model.Product;
import com.example.gms.product.model.dto.ProductCreateReq;
import com.example.gms.product.model.dto.ProductReadRes;
import com.example.gms.product.model.dto.ProductUpdateReq;
import com.example.gms.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void uploadProduct(ProductCreateReq productCreateReq){
        productRepository.save(Product.builder()
                        .name(productCreateReq.getName())
                        .price(productCreateReq.getPrice())
                        .build());
    }

    public List<ProductReadRes> list() {
        List<Product> result = productRepository.findAll();
        List<ProductReadRes> productReadResList = new ArrayList<>();
        for (Product product : result) {
            ProductReadRes productReadRes = ProductReadRes.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
            productReadResList.add(productReadRes);
        }
        return productReadResList;
    }

    public ProductReadRes read(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            Product product = result.get();

            return ProductReadRes.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .name(product.getName())
                    .build();
        }
        return null;
    }
    public void update(ProductUpdateReq productUpdateReq) {
        Optional<Product> result = productRepository.findById(productUpdateReq.getId());
        if(result.isPresent()) {
            Product product = result.get();
            product.setName(productUpdateReq.getName());
            product.setPrice(productUpdateReq.getPrice());

            productRepository.save(product);
        }
    }

    public void delete(Long id) {
        productRepository.delete(Product.builder().id(id).build());
    }

    public Integer getTotalPrice(PaymentProducts datas){

        List<Long> productIds = new ArrayList<>();
        for (Product product: datas.getProducts()) {
            productIds.add(product.getId());
        }

        List<Product> products = productRepository.findAllById(productIds);

        Integer totalPrice = 0;
        for (Product product: products) {
            totalPrice += product.getPrice();
        }

        return  totalPrice;
    }

}
