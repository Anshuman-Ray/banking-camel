package com.example.camelController.routes;

import com.example.camelController.processors.HeaderProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllSavingsAccounts extends RouteBuilder {

    private final HeaderProcessor headerProcessor;

    public void configure() throws Exception {
        from("servlet:api/v1/savingsaccounts?httpMethodRestrict=GET")
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://localhost:8443/fineract-provider/api/v1/savingsaccounts?bridgeEndpoint=true");
    }
}
