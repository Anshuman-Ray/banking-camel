package com.example.camelController.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrintProcess implements Processor {

    private final ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getExchange());
        System.out.println(exchange.getIn().getBody());
        System.out.println(exchange.getIn().getHeaders());
        System.out.println(exchange.getFromRouteId());
    }
}
