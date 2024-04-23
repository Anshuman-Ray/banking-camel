package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private Integer customerNumber;
    private Long clientId;
    private String legal_name;
    private String email;
    private Long mobile_phone_number;
    private Date date_of_birth;
    private String relationship_status;
    private Integer dependants;
    private String highest_education_attained;
    private String employment_status;
}
