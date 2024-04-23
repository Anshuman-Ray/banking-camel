package com.example.camelController.processors;

import com.example.camelController.dto.CustomerResponse;
import com.example.camelController.entity.CustomerEntity;
import com.example.camelController.repository.CustomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppendResponse implements Processor {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        // Convert the request body to a CustomerRequest object
        String jsonBody = exchange.getIn().getBody(String.class);
        JsonNode rootNode = objectMapper.readTree(jsonBody);

        // Read the value of a specific key from the JSON response
        JsonNode valueNode = rootNode.get("active");
        boolean active =  valueNode.asBoolean() ;

        valueNode = rootNode.get("officeId");
        Integer officeId = valueNode != null ? valueNode.asInt() : null;

        valueNode = rootNode.get("officeName");
        String officeName = valueNode != null ? valueNode.asText() : null;


        // Do something with the value
        CustomerResponse customerResponse = exchange.getProperty("customerResponse", CustomerResponse.class);

        customerResponse.setOfficeId(officeId);
        customerResponse.setOfficeName(officeName);
        customerResponse.setActive(active);

        // Set the customer entity as the new body of the exchange
        exchange.getIn().setBody(customerResponse);

        System.out.println("customer response: " + customerResponse);




    }
}
