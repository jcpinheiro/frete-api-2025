package edu.ifma.dcomp.lpweb.frete.domain.repository;

import edu.ifma.dcomp.lpweb.frete.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
     Usuario findByLogin(String login);
}
