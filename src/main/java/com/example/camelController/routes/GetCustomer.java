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
public class GetCustomer extends RouteBuilder {

    private final CustomerProcessor customerProcessor;
    private final HeaderProcessor headerProcessor;
    private final CustomerRequestProcessor customerRequestProcessor;
    private final ClientResponseProcessor clientResponseProcessor;
    private final CustomerRepository customerRepository;
    private final CustomerResponseProcessor customerResponseProcessor;
    private final AppendResponse appendResponse;
    private final GetPathVariable getPathVariable;

    @Override
    public void configure() throws Exception {
        from("servlet:/api/v1/customers/{customerId}?httpMethodRestrict=GET")
                .process(getPathVariable)
                .process(customerResponseProcessor)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://localhost:8443/fineract-provider/api/v1/clients/${exchange.getVariable(\"pathVariable\").toString()}?bridgeEndpoint=true")
                .process(appendResponse)
                .marshal().json(JsonLibrary.Jackson);
    }
}
