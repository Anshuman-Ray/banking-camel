package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveSavingsAccountRequestDTO {
    private String approvedOnDate;
    private String locale;
    private String dateFormat;
}


//{
//        "approvedOnDate": "17 April 2024",
//        "locale": "en",
//        "dateFormat": "dd MMMM yyyy"
//        }