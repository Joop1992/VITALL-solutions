package com.example.vitallsolutions.model.beneficiaries.dto;

import com.example.vitallsolutions.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryRecordDto {
    private String id;
    private User user;
    private User beneficiaryForUser;
    private Double percentage;
}
