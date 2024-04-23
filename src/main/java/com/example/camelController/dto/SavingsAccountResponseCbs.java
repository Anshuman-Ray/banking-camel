package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccountResponseCbs {
    private Long officeId;
    private Long clientId;
    private Long savingsId;
    private Long resourceId;
}
