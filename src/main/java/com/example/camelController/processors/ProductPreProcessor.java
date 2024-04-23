package com.example.camelController.processors;

import com.example.camelController.dto.CustomerRequest;
import com.example.camelController.dto.SavingsAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPreProcessor implements Processor {

    private final ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);
        SavingsAccountRequest requestModel = objectMapper.readValue(jsonBody, SavingsAccountRequest.class);
        exchange.setVariable("productId", requestModel.getProduct_code());
        exchange.setVariable("customerId", requestModel.getCustomer_id());
    }

}
