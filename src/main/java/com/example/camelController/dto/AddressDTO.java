package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Integer addressTypeId;
    private Boolean isActive;
    private String street;
    private Integer stateProvinceId;
    private Integer countryId;
}
