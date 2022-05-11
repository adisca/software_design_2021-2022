package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findByUsername(String username);

}
