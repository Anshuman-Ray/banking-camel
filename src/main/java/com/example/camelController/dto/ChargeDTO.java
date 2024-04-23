package com.example.camelController.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeDTO {
    private Long id;
    private String name;
    private boolean active;
    private boolean penalty;
}
