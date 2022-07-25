package com.example.vitallsolutions.model.bankaccount;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bankaccounts")
public class BankAccount {
    private String id;
    private String userId;
    private String name;
    private String IBAN;

    public BankAccount(String name, String IBAN, String userId) {
        this.name = name;
        this.IBAN = IBAN;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "iban", nullable = false)
    public String getIBAN() {
        return this.IBAN;
    }

    @Column(name = "userId", nullable = false)
    public String getUserId() {
        return this.userId;
    }
}
