package edu.ifma.dcomp.lpweb.frete.controller;

import edu.ifma.dcomp.lpweb.frete.model.Cliente;
import edu.ifma.dcomp.lpweb.frete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
}
