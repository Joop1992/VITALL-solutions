package com.example.vitallsolutions.model.bankaccount.crud.update;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateBankAccountFormData {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 15, max = 60)
    private String id;

    public BankAccountUpdateParameters toParameters() {
        return new BankAccountUpdateParameters(name, id);
    }
}
