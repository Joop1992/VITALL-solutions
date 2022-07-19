package com.example.vitallsolutions.model.bankaccount.crud.create;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class BankAccountCreationParameters {
    private final String name;
    private final String IBAN;

    public BankAccountCreationParameters(String name, String IBAN) {
        Assert.notNull(name, "Name should not be null");
        Assert.notNull(IBAN, "IBAN should not be null");
        this.name = name;
        this.IBAN = IBAN;
    }
}
