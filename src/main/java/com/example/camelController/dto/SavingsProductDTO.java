package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavingsProductDTO {
    private Long id;
    private String name;
    private String shortName;
    private String description;
    private CurrencyDTO currency;
    private double nominalAnnualInterestRate;
    private InterestPeriodTypeDTO interestCompoundingPeriodType;
    private InterestPeriodTypeDTO interestPostingPeriodType;
    private InterestCalculationTypeDTO interestCalculationType;
    private InterestCalculationDaysInYearTypeDTO interestCalculationDaysInYearType;
    private boolean withdrawalFeeForTransfers;
    private AccountingRuleTypeDTO accountingRule;
    private AccountingMappingsDTO accountingMappings;
    private List<PaymentChannelToFundSourceMappingDTO> paymentChannelToFundSourceMappings;
    private List<FeeToIncomeAccountMappingDTO> feeToIncomeAccountMappings;
    private List<PenaltyToIncomeAccountMappingDTO> penaltyToIncomeAccountMappings;
    private List<String> charges;
}
