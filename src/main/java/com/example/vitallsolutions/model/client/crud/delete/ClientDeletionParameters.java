package com.example.vitallsolutions.model.client.crud.delete;

import com.okta.commons.lang.Assert;
import lombok.Data;

@Data
public class ClientDeletionParameters {
    private final String id;

    public ClientDeletionParameters(String id) {
        Assert.notNull(id, "Id should not be null");
        this.id = id;
    }
}
