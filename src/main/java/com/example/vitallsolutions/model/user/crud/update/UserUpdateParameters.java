package com.example.vitallsolutions.model.user.crud.update;

import com.example.vitallsolutions.model.user.UserStatus;
import com.okta.commons.lang.Assert;
import lombok.Data;

import java.util.Arrays;

@Data
public class UserUpdateParameters {
    private final Double fee;
    private final String status;
    private final String id;
    private final String email;

    public UserUpdateParameters(Double fee, String status, String id, String email) {
        Assert.notNull(fee, "Fee should not be null");
        Assert.notNull(status, "Status should not be null");
        Assert.notNull(email, "Email should not be null");
        Assert.notNull(id, "ID should not be null");
        Assert.isTrue(Arrays.asList(UserStatus.ACTIVE, UserStatus.INACTIVE).contains((status)));

        this.fee = fee;
        this.status = status;
        this.id = id;
        this.email = email;
    }
}
