package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountingMappingsDTO {
    private AccountingMappingDTO savingsReferenceAccount;
    private AccountingMappingDTO incomeFromFeeAccount;
    private AccountingMappingDTO incomeFromPenaltyAccount;
    private AccountingMappingDTO interestOnSavingsAccount;
    private AccountingMappingDTO savingsControlAccount;
    private AccountingMappingDTO transfersInSuspenseAccount;
}
