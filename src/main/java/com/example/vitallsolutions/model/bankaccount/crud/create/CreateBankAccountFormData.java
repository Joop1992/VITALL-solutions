package com.example.vitallsolutions.model.bankaccount.crud.create;

import com.example.vitallsolutions.model.bankaccount.crud.create.BankAccountCreationParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateBankAccountFormData {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 15, max = 60)
    private String IBAN;

    public BankAccountCreationParameters toParameters() {
        return new BankAccountCreationParameters(name, IBAN);
    }
}
