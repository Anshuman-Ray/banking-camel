package com.example.camelController.processors;

import com.example.camelController.entity.CustomerEntity;
import com.example.camelController.repository.CustomerRepository;
import com.example.camelController.dto.CustomerRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientResponseProcessor implements Processor {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        // Convert the request body to a CustomerRequest object
        String jsonBody = exchange.getIn().getBody(String.class);
        JsonNode rootNode = objectMapper.readTree(jsonBody);

        // Read the value of a specific key from the JSON response
        JsonNode valueNode = rootNode.get("clientId");
        Integer clientId = valueNode != null ? valueNode.asInt() : null;

        // Do something with the value
        CustomerEntity customer = exchange.getProperty("customer", CustomerEntity.class);

        customer.setClientId(clientId);

        // Set the customer entity as the new body of the exchange
        exchange.getIn().setBody(customer);

        // Save the customer entity to the database
        customerRepository.save(customer);


    }
}
