package com.example.camelController.processors;

import com.example.camelController.dto.CustomerRequest;
import com.example.camelController.dto.CustomerRequestCBS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerProcessorTest {

    private final CustomerProcessor customerProcessor;

    public CustomerProcessorTest(ObjectMapper objectMapper, CustomerProcessor customerProcessor) {
        this.customerProcessor = customerProcessor;
    }

    @Test
    public void testProcess() throws Exception {
        CamelContext context = new DefaultCamelContext();
        // Given
        Exchange exchange = ExchangeBuilder.anExchange(context).withBody("{\"customerNumber\":1,\"legal_name\":\"John Doe\"}").build();
//        CustomerProcessor customerProcessor = new CustomerProcessor(objectMapper);
//
        // When
        customerProcessor.process(exchange);

        // Then
        CustomerRequestCBS cbsRequest = exchange.getIn().getBody(CustomerRequestCBS.class);
        assertNotNull(cbsRequest);
        assertEquals("John", cbsRequest.getFirstname());
        assertEquals("Doe", cbsRequest.getLastname());
//        assertTrue(cbsRequest.isActive());
        assertEquals("16 April 2024", cbsRequest.getActivationDate());
        // Add more assertions based on the mapping logic
    }

    // Add more test cases to cover edge cases and different scenarios
}


