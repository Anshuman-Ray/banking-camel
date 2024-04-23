package com.example.camelController.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
import java.sql.Date;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Integer customerId;

    @Column(name = "client_id", unique = true)
    private Integer clientId;

    @Column(name = "customer_number", unique = true)
    private Integer customerNumber;

    @Column(name = "legal_name", nullable = false)
    private String legalName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_phone_number")
    private Long mobilePhoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "relationship_status")
    private String relationshipStatus;

    @Column(name = "dependants")
    private Integer dependants;

    @Column(name = "highest_education_attained")
    private String highestEducationAttained;

    @Column(name = "employment_status")
    private String employmentStatus;

    // Add more fields as needed

    // Constructor with mandatory fields
    public CustomerEntity(Integer customerNumber, String legalName, String email, Date dateOfBirth) {
        this.customerNumber = customerNumber;
        this.legalName = legalName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
