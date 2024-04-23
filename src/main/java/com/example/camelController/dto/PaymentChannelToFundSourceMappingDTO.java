package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentChannelToFundSourceMappingDTO {
    private PaymentTypeDTO paymentType;
    private AccountingMappingDTO fundSourceAccount;
}
