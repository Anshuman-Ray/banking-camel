package com.example.camelController.processors;

import com.example.camelController.entity.CustomerEntity;
import com.example.camelController.repository.CustomerRepository;
import com.example.camelController.dto.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRequestProcessor implements Processor {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        // Convert the request body to a CustomerRequest object
        String jsonBody = exchange.getIn().getBody(String.class);
        CustomerRequest customerRequest = objectMapper.readValue(jsonBody, CustomerRequest.class);

        // Create a Customer entity from the CustomerRequest
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerNumber(customerRequest.getCustomerNumber());
        customer.setLegalName(customerRequest.getLegal_name());
        customer.setEmail(customerRequest.getEmail());
        customer.setMobilePhoneNumber(customerRequest.getMobile_phone_number());
        customer.setDateOfBirth(customerRequest.getDate_of_birth());
        customer.setRelationshipStatus(customerRequest.getRelationship_status());
        customer.setHighestEducationAttained(customerRequest.getHighest_education_attained());
        customer.setEmploymentStatus(customerRequest.getEmployment_status());

        // Save the customer entity to the database
        //customerRepository.save(customer);

        // Set the customer entity as the new body of the exchange
        //exchange.getIn().setBody(customer);

        // Set the customer entity as a property of the exchange
        exchange.setProperty("customer", customer);

    }
}
