package com.example.vitallsolutions.model.client.service;

import com.example.vitallsolutions.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    public List<Client> findByUserId(String userId);
    public Optional<Client> findByIdAndUserId(String id, String userId);
}
