package com.parsing.repository;

import com.parsing.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where c.inn = ?1")
    Client findClientByInn(Long inn);
}
