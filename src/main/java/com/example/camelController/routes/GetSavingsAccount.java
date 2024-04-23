package com.example.camelController.routes;

import com.example.camelController.processors.GetPathVariable;
import com.example.camelController.processors.HeaderProcessor;
import com.example.camelController.processors.PrintProcess;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetSavingsAccount extends RouteBuilder {

    private final HeaderProcessor headerProcessor;
    private final PrintProcess printProcess;
    private final GetPathVariable getPathVariable;

    public void configure() throws Exception {
        from("servlet:api/v1/savingsaccount/{savingsId}")
                .process(getPathVariable)
                .process(printProcess)
                .process(headerProcessor)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://localhost:8443/fineract-provider/api/v1/savingsaccounts/${exchange.getVariable(\"pathVariable\").toString()}?bridgeEndpoint=true");
    }
}
