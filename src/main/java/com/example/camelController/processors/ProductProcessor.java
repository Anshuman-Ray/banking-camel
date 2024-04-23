package com.example.camelController.processors;

import com.example.camelController.dto.CustomerRequest;
import com.example.camelController.dto.SavingsAccountRequest;
import com.example.camelController.dto.SavingsAccountRequestCbs;
import com.example.camelController.dto.SavingsProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProductProcessor implements Processor {

    private final ObjectMapper objectMapper;

    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);
        SavingsProductDTO savingsProductDTO = objectMapper.readValue(jsonBody, SavingsProductDTO.class);
        SavingsAccountRequestCbs CbsRequest = SavingsAccountRequestCbs.builder()
                .nominalAnnualInterestRate(savingsProductDTO.getNominalAnnualInterestRate())
                .productId(savingsProductDTO.getId())
                .charges(new ArrayList<>())
                .locale("en")
                .dateFormat("dd MMMM yyyy")
                .allowOverdraft(false)
                .clientId(exchange.getVariable("customerId").toString())
                .enforceMinRequiredBalance(false)
                .submittedOnDate("23 April 2024")
                .interestCalculationType(savingsProductDTO.getInterestCalculationType().getId())
                .interestCompoundingPeriodType(savingsProductDTO.getInterestCompoundingPeriodType().getId())
                .withHoldTax(false)
                .interestPostingPeriodType(savingsProductDTO.getInterestPostingPeriodType().getId())
                .monthDayFormat("dd MMM")
                .interestCalculationDaysInYearType(savingsProductDTO.getInterestCalculationDaysInYearType().getId())
                .withdrawalFeeForTransfers(false)
                .build();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(CbsRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        exchange.getIn().setBody(json);
    }

}
