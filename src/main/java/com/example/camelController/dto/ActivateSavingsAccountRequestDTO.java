package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivateSavingsAccountRequestDTO {
    private String activatedOnDate;
    private String locale;
    private String dateFormat;
}


//{
//        "activatedOnDate": "17 April 2024",
//        "locale": "en",
//        "dateFormat": "dd MMMM yyyy"
//        }