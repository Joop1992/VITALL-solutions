package com.example.vitallsolutions.model.client.crud.create;

import com.example.vitallsolutions.model.bankaccount.crud.create.BankAccountCreationParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateClientFormData {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 4, max = 100)
    private String email;

    @NotNull
    @Size(min = 3, max = 200)
    private String contactName;

    private String address;

    public ClientCreationParameters toParameters() {
        return new ClientCreationParameters(name, email, contactName, address);
    }
}
