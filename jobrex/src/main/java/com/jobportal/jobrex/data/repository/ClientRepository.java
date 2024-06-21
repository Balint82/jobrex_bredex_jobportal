package com.jobportal.jobrex.data.repository;

import com.jobportal.jobrex.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByApiKey(String UUID);
    Optional<Client> findByEmail(String email);
}
