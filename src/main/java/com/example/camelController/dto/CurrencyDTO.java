package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    private String code;
    private String name;
    private int decimalPlaces;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;
}
