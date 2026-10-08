package edu.ifma.dcomp.lpweb.frete.repository;

import edu.ifma.dcomp.lpweb.frete.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }

