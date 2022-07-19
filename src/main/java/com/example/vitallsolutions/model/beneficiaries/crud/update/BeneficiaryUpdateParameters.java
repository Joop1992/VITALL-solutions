package com.example.vitallsolutions.model.beneficiaries.crud.update;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BeneficiaryUpdateParameters {
    private final Double percentage;
    private final String id;

    public BeneficiaryUpdateParameters(Double percentage, String id) {
        Assert.notNull(percentage, "Percentage should not be null");
        Assert.notNull(id, "ID should not be null");
        this.percentage = percentage;
        this.id = id;
    }
}
