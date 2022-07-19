package com.example.vitallsolutions.model.bankaccount.crud.delete;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BankAccountDeletionParameters {
    private final String id;

    public BankAccountDeletionParameters(String id) {
        Assert.notNull(id, "Id should not be null");
        this.id = id;
    }
}
