package com.example.camelController.processors;

import com.example.camelController.dto.CustomerRequest;
import com.example.camelController.dto.CustomerRequestCBS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("dataProcessor")
@RequiredArgsConstructor
public class CustomerProcessor implements Processor {

    private final ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        String jsonBody = exchange.getIn().getBody(String.class);
        CustomerRequest requestModel = objectMapper.readValue(jsonBody, CustomerRequest.class);

        System.out.println("Recieved Request: " + requestModel.toString());

        CustomerRequestCBS cbsRequest = CustomerRequestCBS.builder()
                .active(true)
                .activationDate("16 April 2024")
                .firstname(requestModel.getLegal_name().split(" ")[0])
                .lastname(requestModel.getLegal_name().split(" ")[1])
                .locale("en")
                .dateFormat("dd MMMM yyyy")
                .officeId(1)
                .savingsProductId(null)
                .submittedOnDate("16 April 2024")
                .legalFormId(1)
                .address(new String[0])
                .familyMembers(new String[0])
                .build();

        exchange.getIn().setBody(cbsRequest);
    }

}
