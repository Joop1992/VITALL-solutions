package com.example.vitallsolutions.model.bankaccount.service;

import com.example.vitallsolutions.model.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    public List<BankAccount> findByUserId(String userId);
    public Optional<BankAccount> findByIdAndUserId(String id, String userId);
}