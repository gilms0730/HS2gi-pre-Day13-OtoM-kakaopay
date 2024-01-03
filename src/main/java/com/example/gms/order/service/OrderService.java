package com.example.gms.order.service;

import com.example.gms.order.model.GetOrdersRes;
import com.example.gms.order.model.PaymentProducts;
import com.example.gms.order.repository.OrderedProductRepository;
import com.example.gms.product.model.*;
import com.example.gms.order.model.Order;
import com.example.gms.order.model.OrderedProduct;
import com.example.gms.order.repository.OrderRepository;
import com.example.gms.product.model.dto.ProductReadRes;
import com.example.gms.product.service.ProductService;
import com.example.gms.user.model.User;
import com.google.gson.Gson;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final IamportClient iamportClient;

    private final ProductService productService;

    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;

    public OrderService(IamportClient iamportClient, ProductService productService, OrderRepository orderRepository, OrderedProductRepository orderedProductRepository) {
        this.iamportClient = iamportClient;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderedProductRepository=orderedProductRepository;
    }
    public List<GetOrdersRes> list(){
        List<Order> orderList = orderRepository.findAll();
        List<GetOrdersRes> response = new ArrayList<>();

        for(Order order : orderList){
            List<ProductReadRes> productReadResList = new ArrayList<>();
            for (OrderedProduct orderedProduct: order.getOrderedProductList()){
                ProductReadRes productReadRes = ProductReadRes.builder()
                        .id(orderedProduct.getProduct().getId())
                        .name(orderedProduct.getProduct().getName())
                        .price(orderedProduct.getProduct().getPrice())
                        .build();
                productReadResList.add(productReadRes);
            }
            GetOrdersRes getOrdersRes = GetOrdersRes.builder()
                    .id(order.getId())
                    .userName(order.getUser().getName())
                    .products(productReadResList)
                    .build();
            response.add(getOrdersRes);
        }
        return response;
    }
    public void createOrder(String imUid, PaymentProducts paymentProducts){
        Order order = Order.builder()
                .user(User.builder().id(1L).build())
                .impUid(imUid)
                .build();
        order = orderRepository.save(order);
        for(Product product: paymentProducts.getProducts()){
            orderedProductRepository.save(OrderedProduct.builder()
                    .order(order)
                    .product(product)
                    .build());
        }
    }

    public Boolean paymentValidation(String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = getPaymentInfo(impUid);
        Integer amount = response.getResponse().getAmount().intValue();

        String customDataString = response.getResponse().getCustomData();
        Gson gson = new Gson();
        PaymentProducts paymentProducts = gson.fromJson(customDataString, PaymentProducts.class);

        Integer totalPrice = productService.getTotalPrice(paymentProducts);

        if(amount.equals(totalPrice) ) {
            createOrder(impUid, paymentProducts);
            return true;
        }

        return false;

    }
    public IamportResponse getPaymentInfo(String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
        return response;
    }
}
