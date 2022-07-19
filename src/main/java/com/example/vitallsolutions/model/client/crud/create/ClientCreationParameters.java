package com.example.vitallsolutions.model.client.crud.create;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class ClientCreationParameters {
    private final String name;
    private final String email;
    private final String contactName;
    private final String address;

    public ClientCreationParameters(String name, String email, String contactName, String address) {
        Assert.notNull(name, "Name should not be null");
        Assert.notNull(email, "Email should not be null");
        Assert.notNull(contactName, "Contact name should not be null");

        this.name = name;
        this.email = email;
        this.contactName = contactName;
        this.address = address;
    }
}
