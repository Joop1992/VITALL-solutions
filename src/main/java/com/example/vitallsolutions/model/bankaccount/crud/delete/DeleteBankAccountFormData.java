package com.example.vitallsolutions.model.bankaccount.crud.delete;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DeleteBankAccountFormData {
    @NotNull
    @Size(min = 20, max = 50)
    private String id;

    public BankAccountDeletionParameters toParameters() {
        return new BankAccountDeletionParameters(id);
    }
}