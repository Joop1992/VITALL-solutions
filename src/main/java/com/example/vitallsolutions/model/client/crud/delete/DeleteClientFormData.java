package com.example.vitallsolutions.model.client.crud.delete;

import com.example.vitallsolutions.model.bankaccount.crud.delete.BankAccountDeletionParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DeleteClientFormData {
    @NotNull
    @Size(min = 20, max = 50)
    private String id;

    public ClientDeletionParameters toParameters() {
        return new ClientDeletionParameters(id);
    }
}
