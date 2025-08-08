package com.technumen.orderservicenollm.service;

import com.technumen.orderservicenollm.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrdServ {
    private final RestTemplate rst = new RestTemplate();

    //Defining base URL
    private final String url = "http://localhost:8080";


    //Get response from remote API
    private ResponseEntity<Order> getResponse(Long custId) {
        return rst.getForEntity(url + "/" + custId, Order.class);
    }


    //Check for 200 OK Status if Customer exists
    public boolean doesExist(Long custId) {
        return getResponse(custId).getStatusCode() == HttpStatus.OK;
    }
}