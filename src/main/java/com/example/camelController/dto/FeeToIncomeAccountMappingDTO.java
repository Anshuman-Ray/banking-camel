package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeToIncomeAccountMappingDTO {
    private ChargeDTO charge;
    private AccountingMappingDTO incomeAccount;
}
