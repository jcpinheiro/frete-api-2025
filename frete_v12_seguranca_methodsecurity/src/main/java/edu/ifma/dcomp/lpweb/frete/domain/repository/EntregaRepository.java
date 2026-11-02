package edu.ifma.dcomp.lpweb.frete.domain.repository;


import edu.ifma.dcomp.lpweb.frete.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}