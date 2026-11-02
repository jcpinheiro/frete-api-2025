package edu.ifma.dcomp.lpweb.frete.security;

import edu.ifma.dcomp.lpweb.frete.domain.model.Usuario;
import edu.ifma.dcomp.lpweb.frete.domain.service.UsuarioService;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;

    public CustomUserDetailsService(UsuarioService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}
