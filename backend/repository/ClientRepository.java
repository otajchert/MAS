package com.zbyszkobud.repository;

import com.zbyszkobud.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
