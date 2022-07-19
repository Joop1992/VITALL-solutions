package com.example.vitallsolutions.model.beneficiaries.service;

import com.example.vitallsolutions.model.beneficiaries.Beneficiary;
import com.example.vitallsolutions.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, String> {
    public List<Beneficiary> findByUserIdAndBeneficiaryForUserId(String userId, String beneficiaryForUserId);
}
