package com.example.vitallsolutions.model.beneficiaries.crud.delete;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BeneficiaryDeletionParameters {
    private final String id;

    public BeneficiaryDeletionParameters(String id) {
        Assert.notNull(id, "Id should not be null");
        this.id = id;
    }
}
