package com.example.vitallsolutions.model.client.crud.update;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class ClientUpdateParameters {
    private final String name;
    private final String email;
    private final String contactName;
    private final String address;
    private final String id;

    public ClientUpdateParameters(String name, String email, String contactName, String address, String id) {
        Assert.notNull(name, "Name should not be null");
        Assert.notNull(id, "ID should not be null");
        Assert.notNull(email, "Email should not be null");
        Assert.notNull(contactName, "Contact name should not be null");
        this.name = name;
        this.id = id;
        this.email = email;
        this.contactName = contactName;
        this.address = address;
    }
}
