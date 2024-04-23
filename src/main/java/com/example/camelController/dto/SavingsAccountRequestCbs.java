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
public class SavingsAccountRequestCbs {

    private Long productId;
    private double nominalAnnualInterestRate;
    private boolean withdrawalFeeForTransfers;
    private boolean allowOverdraft;
    private boolean enforceMinRequiredBalance;
    private boolean withHoldTax;
    private Long interestCompoundingPeriodType;
    private Long interestPostingPeriodType;
    private Long interestCalculationType;
    private Long interestCalculationDaysInYearType;
    private String submittedOnDate;
    private String locale;
    private String dateFormat;
    private String monthDayFormat;
    private List<String> charges;
    private String clientId;
}

//{
//        "productId": 1,
//        "nominalAnnualInterestRate": 5,
//        "withdrawalFeeForTransfers": false,
//        "allowOverdraft": false,
//        "enforceMinRequiredBalance": false,
//        "withHoldTax": false,
//        "interestCompoundingPeriodType": 7,
//        "interestPostingPeriodType": 7,
//        "interestCalculationType": 1,
//        "interestCalculationDaysInYearType": 365,
//        "submittedOnDate": "17 April 2024",
//        "locale": "en",
//        "dateFormat": "dd MMMM yyyy",
//        "monthDayFormat": "dd MMM",
//        "charges": [],
//        "clientId": "1"
//        }


//{
//        "approvedOnDate": "17 April 2024",
//        "locale": "en",
//        "dateFormat": "dd MMMM yyyy"
//        }

//{
//        "activatedOnDate": "17 April 2024",
//        "locale": "en",
//        "dateFormat": "dd MMMM yyyy"
//        }