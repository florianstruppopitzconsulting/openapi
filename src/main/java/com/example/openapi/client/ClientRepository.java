package com.example.openapi.client;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, Long> {

    Optional<Client> findByClientId(Long clientId);

    void deleteByClientId(Long clientId);
}
