package com.example.vitallsolutions.model.beneficiaries;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "beneficiaries")
public class Beneficiary {
    private String id;
    private String userId;
    private String beneficiaryForUserId;
    private Double percentage;

    public Beneficiary(String userId, String beneficiaryForUserId) {
        this.userId = userId;
        this.beneficiaryForUserId = beneficiaryForUserId;
        this.percentage = 50.0;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    @Column(name = "userId", nullable = false)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "beneficiaryForUserId", nullable = false)
    public String getBeneficiaryForUserId() {
        return beneficiaryForUserId;
    }

    public void setBeneficiaryForUserId(String beneficiaryForUserId) {
        this.beneficiaryForUserId = beneficiaryForUserId;
    }

    @Column(name = "percentage", nullable = false)
    public Double getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}

