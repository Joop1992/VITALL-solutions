package com.example.vitallsolutions.model.user.crud.update;

import com.example.vitallsolutions.model.bankaccount.crud.update.BankAccountUpdateParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateUserFormData {
    @NotNull
    private Double fee;

    @NotNull
    @Size(min = 6, max = 8)
    private String status;

    @NotNull
    @Size(min = 10, max = 60)
    private String id;

    @NotNull
    @Size(min = 7, max = 100)
    private String email;

    public UserUpdateParameters toParameters() {
        return new UserUpdateParameters(fee, status, id, email);
    }
}
