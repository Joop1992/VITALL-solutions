package com.example.vitallsolutions.model.beneficiaries.crud.update;

import com.example.vitallsolutions.model.bankaccount.crud.update.BankAccountUpdateParameters;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateBeneficiaryFormData {
    @NotNull
    private Double percentage;

    @NotNull
    @Size(min = 15, max = 60)
    private String id;

    public BeneficiaryUpdateParameters toParameters() {
        return new BeneficiaryUpdateParameters(percentage, id);
    }
}
