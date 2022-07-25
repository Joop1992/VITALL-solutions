package com.example.vitallsolutions.model.invoice.service;

import com.example.vitallsolutions.model.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    public List<Invoice> findByUserId(String userId);
    public Optional<Invoice> findByIdAndUserId(String id, String userId);
}