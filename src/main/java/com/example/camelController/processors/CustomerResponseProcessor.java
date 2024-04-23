package com.example.camelController.processors;

import com.example.camelController.dto.CustomerResponse;
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
public class CustomerResponseProcessor implements Processor {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;


    @Override
    public void process(Exchange exchange) throws Exception {
        // Convert the request body to a CustomerRequest object

        CustomerEntity customerEntity = customerRepository.findById(Integer.parseInt(exchange.getVariable("pathVariable").toString()));

//        String jsonBody = exchange.getIn().getBody(String.class);
//                CustomerEntity customerEntity = objectMapper.readValue(jsonBody, CustomerEntity.class);



        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerNumber(customerEntity.getCustomerNumber());
        customerResponse.setLegal_name(customerEntity.getLegalName());
        customerResponse.setEmail(customerEntity.getEmail());
        customerResponse.setMobile_phone_number(customerEntity.getMobilePhoneNumber());
        customerResponse.setDate_of_birth(customerEntity.getDateOfBirth());
        customerResponse.setRelationship_status(customerEntity.getRelationshipStatus());
        customerResponse.setHighest_education_attained(customerEntity.getHighestEducationAttained());
        customerResponse.setEmployment_status(customerEntity.getEmploymentStatus());

        // Save the customer entity to the database
        //customerRepository.save(customer);

        // Set the customer entity as the new body of the exchange
        //exchange.getIn().setBody(customer);

        // Set the customer entity as a property of the exchange
        exchange.setProperty("customerResponse", customerResponse);

        exchange.getIn().setBody(customerResponse);

    }
}
