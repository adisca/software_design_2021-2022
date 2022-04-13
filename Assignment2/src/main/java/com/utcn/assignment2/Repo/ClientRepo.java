package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findByUsername(String username);

}
