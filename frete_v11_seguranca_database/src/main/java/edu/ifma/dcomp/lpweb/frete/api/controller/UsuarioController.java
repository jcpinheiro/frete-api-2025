package edu.ifma.dcomp.lpweb.frete.api.controller;

import edu.ifma.dcomp.lpweb.frete.api.dto.input.UsuarioDTO;
import edu.ifma.dcomp.lpweb.frete.api.mapper.UsuarioMapper;
import edu.ifma.dcomp.lpweb.frete.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario );
    }
}
