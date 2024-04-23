package com.example.camelController.routes;

import com.example.camelController.processors.*;
import com.example.camelController.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomer extends RouteBuilder {

    private final CustomerProcessor customerProcessor;
    private final HeaderProcessor headerProcessor;
    private final CustomerRequestProcessor customerRequestProcessor;
    private final ClientResponseProcessor clientResponseProcessor;
    private final CustomerRepository customerRepository;
    private final CustomerResponseProcessor customerResponseProcessor;
    private final AppendResponse appendResponse;

    @Override
    public void configure() throws Exception {
        from("servlet:/api/v1/customers?httpMethodRestrict=POST")
                .process(customerRequestProcessor)
                .process(customerProcessor)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .marshal().json(JsonLibrary.Jackson)
                .to("http://localhost:8443/fineract-provider/api/v1/clients?bridgeEndpoint=true")
                .process(clientResponseProcessor);


    }
}
