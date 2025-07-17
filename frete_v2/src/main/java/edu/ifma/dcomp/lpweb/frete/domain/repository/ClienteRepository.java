package edu.ifma.dcomp.lpweb.frete.domain.repository;

import edu.ifma.dcomp.lpweb.frete.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeContaining(String nome );
}
