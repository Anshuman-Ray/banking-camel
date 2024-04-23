package com.example.camelController.routes;

import com.example.camelController.processors.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavingsAccountRoute extends RouteBuilder {

    private final ProductPreProcessor productPreProcessor;
    private final ProductProcessor productProcessor;
    private final HeaderProcessor headerProcessor;
    private final SavingsAccountApproveProcessor savingsAccountApproveProcessor;
    private final SavingsAccountActivateProcessor savingsAccountActivateProcessor;
    private final ObjectMapper objectMapper;
    private final PrintProcess printProcess;


    public void configure() throws Exception {
        from("servlet:/api/v1/savingsaccounts?httpMethodRestrict=POST")
                .process(productPreProcessor)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://localhost:8443/fineract-provider/api/v1/savingsproducts/${exchange.getVariable(\"productId\").toString()}?bridgeEndpoint=true")
                .process(productProcessor)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to("http://localhost:8443/fineract-provider/api/v1/savingsaccounts?bridgeEndpoint=true")
                .process(savingsAccountApproveProcessor)
                .removeHeaders("*")
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .process(printProcess)
                .toD("http://localhost:8443/fineract-provider/api/v1/savingsaccounts/${exchange.getVariable(\"savingsId\").toString()}?command=approve&bridgeEndpoint=true")
                .process(savingsAccountActivateProcessor)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .toD("http://localhost:8443/fineract-provider/api/v1/savingsaccounts/${exchange.getVariable(\"savingsId\").toString()}?command=activate&bridgeEndpoint=true");

    }
}
