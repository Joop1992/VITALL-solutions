package com.example.vitallsolutions.model.client.crud.update;

import com.example.vitallsolutions.model.bankaccount.crud.update.BankAccountUpdateParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateClientFormData {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 15, max = 60)
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String contactName;

    @NotNull
    @Size(min = 4, max = 100)
    private String email;

    private String address;

    public ClientUpdateParameters toParameters() {
        return new ClientUpdateParameters(name, email, contactName, address, id);
    }
}
