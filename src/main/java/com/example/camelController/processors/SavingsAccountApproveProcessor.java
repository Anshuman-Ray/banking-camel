package com.example.camelController.processors;

import com.example.camelController.dto.ApproveSavingsAccountRequestDTO;
import com.example.camelController.dto.SavingsAccountResponseCbs;
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
public class SavingsAccountApproveProcessor implements Processor {

    private final ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);
        SavingsAccountResponseCbs savingsAccountResponseCbs = objectMapper.readValue(jsonBody, SavingsAccountResponseCbs.class);
        exchange.setVariable("savingsId", savingsAccountResponseCbs.getSavingsId());
        ApproveSavingsAccountRequestDTO approveSavingsAccountRequestDTO = ApproveSavingsAccountRequestDTO.builder()
                .approvedOnDate("23 April 2024")
                .dateFormat("dd MMMM yyyy")
                .locale("en")
                .build();
        String approvalJson = "";
        try {
            approvalJson = objectMapper.writeValueAsString(approveSavingsAccountRequestDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        exchange.getIn().setBody(approvalJson);
        System.out.println(approvalJson);
    }
}
