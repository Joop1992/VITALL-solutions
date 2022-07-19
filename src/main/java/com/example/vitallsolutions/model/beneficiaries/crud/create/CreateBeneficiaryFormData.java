package com.example.vitallsolutions.model.beneficiaries.crud.create;

import com.example.vitallsolutions.model.bankaccount.crud.create.BankAccountCreationParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateBeneficiaryFormData {
    @NotNull
    @Size(min = 1, max = 50)
    private String userId;

    @NotNull
    @Size(min = 1, max = 60)
    private String beneficiaryForUserID;

    public BeneficiaryCreationParameters toParameters() {
        return new BeneficiaryCreationParameters(userId, beneficiaryForUserID);
    }
}
