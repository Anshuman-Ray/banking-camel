package com.example.camelController.processors;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPathVariable implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String path=exchange.getIn().getHeader("CamelHttpPath", String.class);
        String[] pathVarArr=path.split("/");
        String pathVar = pathVarArr[pathVarArr.length-1];
        exchange.setVariable("pathVariable", Integer.parseInt(pathVar));
    }
}