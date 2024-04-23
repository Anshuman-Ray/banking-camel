package com.example.camelController.routes;

import com.example.camelController.processors.ClientResponseProcessor;
import com.example.camelController.processors.CustomerProcessor;
import com.example.camelController.processors.CustomerRequestProcessor;
import com.example.camelController.processors.HeaderProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class CustomerRoute extends RouteBuilder {

    private final CustomerProcessor customerProcessor;
    private final HeaderProcessor headerProcessor;
    private final CustomerRequestProcessor customerRequestProcessor;
    private final ClientResponseProcessor clientResponseProcessor;

    @Override
    public void configure() throws Exception {
        from("servlet:/api/v1/customers?httpMethodRestrict=POST")
                .process(customerRequestProcessor)
                .process(customerProcessor)
                .process(headerProcessor)
                .marshal().json(JsonLibrary.Jackson)
                .to("http://localhost:8443/fineract-provider/api/v1/clients?bridgeEndpoint=true")
                .process(clientResponseProcessor);



    }
}
