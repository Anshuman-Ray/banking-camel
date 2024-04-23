package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestCBS {

    private String[] address;
    private String[] familyMembers;
    private Integer officeId;
    private Integer legalFormId;
    private String firstname;
    private String lastname;
    private String locale;
    private Boolean active;
    private String dateFormat;
    private String activationDate;
    private String submittedOnDate;
    private Integer savingsProductId;

}