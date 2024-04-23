package com.example.camelController.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class HeaderProcessor implements Processor {

    @Value("${my.superUsername}")
    private String superUsername;
    @Value("${my.superUserPassword}")
    private String superUserPassword;

    @Override
    public void process(Exchange exchange) throws Exception {
        String auth = superUsername + ":" + superUserPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getIn().setHeader("Authorization", authHeader);
        exchange.getIn().setHeader("Accept", "application/json, text/plain, */*");
        exchange.getIn().setHeader("Accept-Language", "en-US,en;q=0.9,hi;q=0.8");
        exchange.getIn().setHeader("Content-Type", "application/json;charset=UTF-8");
        exchange.getIn().setHeader("Fineract-Platform-TenantId", "default");
    }
}
