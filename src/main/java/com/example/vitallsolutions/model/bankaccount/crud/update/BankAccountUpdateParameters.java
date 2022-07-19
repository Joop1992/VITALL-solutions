package com.example.vitallsolutions.model.bankaccount.crud.update;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BankAccountUpdateParameters {
    private final String name;
    private final String id;

    public BankAccountUpdateParameters(String name, String id) {
        Assert.notNull(name, "Name should not be null");
        Assert.notNull(id, "ID should not be null");
        this.name = name;
        this.id = id;
    }
}
