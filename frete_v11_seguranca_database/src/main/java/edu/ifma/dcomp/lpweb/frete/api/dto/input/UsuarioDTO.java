package edu.ifma.dcomp.lpweb.frete.api.dto.input;

import java.util.List;

public record UsuarioDTO(String login, String senha, List<String> roles) { }
