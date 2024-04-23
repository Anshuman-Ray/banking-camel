package com.example.camelController.processors;

import com.example.camelController.dto.ActivateSavingsAccountRequestDTO;
import com.example.camelController.dto.SavingsProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavingsAccountActivateProcessor implements Processor {

    private final ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);
        SavingsProductDTO savingsProductDTO = objectMapper.readValue(jsonBody, SavingsProductDTO.class);
        ActivateSavingsAccountRequestDTO activateSavingsAccountRequestDTO = ActivateSavingsAccountRequestDTO.builder()
                .activatedOnDate("23 April 2024")
                .dateFormat("dd MMMM yyyy")
                .locale("en")
                .build();
        String activateJson = "";
        try {
            activateJson = objectMapper.writeValueAsString(activateSavingsAccountRequestDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        exchange.getIn().setBody(activateJson);
    }

}
