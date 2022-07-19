package com.example.vitallsolutions.model.beneficiaries.crud.create;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BeneficiaryCreationParameters {
    private final String userId;
    private final String beneficiaryForUserId;

    public BeneficiaryCreationParameters(String userId, String beneficiaryForUserId) {
        Assert.notNull(userId, "User ID should not be null");
        Assert.notNull(beneficiaryForUserId, "Beneficiary for user id should not be null");
        this.userId = userId;
        this.beneficiaryForUserId = beneficiaryForUserId;
    }
}
