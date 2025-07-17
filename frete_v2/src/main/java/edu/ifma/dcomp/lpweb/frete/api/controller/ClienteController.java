package edu.ifma.dcomp.lpweb.frete.api.controller;

import edu.ifma.dcomp.lpweb.frete.domain.model.Cliente;
import edu.ifma.dcomp.lpweb.frete.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.service = clienteService;
    }

  // versao 01
  @GetMapping
  public List<Cliente> lista(String nome ) {
        if (nome == null ) {
            return service.todos();
        } else {
            return service.buscaPor(nome );
        }
    }

//versao 01
    @GetMapping("/v1/{id}")
    public Cliente buscaPor(@PathVariable Integer id ) {
        return service.buscaPor(id ).orElse(null);
    }

    //versao 02
    @GetMapping("/v2/{id}")
    public ResponseEntity<Cliente> buscaPorV2(@PathVariable Integer id) {
        Optional<Cliente> optional = service.buscaPor(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/v3/{id}")
    public ResponseEntity<Cliente> buscaPorV3(@PathVariable Integer id) {
       return service.buscaPor(id)
               .map(ResponseEntity::ok )   //.map(cliente -> ResponseEntity.ok(cliente))
               .orElse(ResponseEntity.notFound().build());

    }

    // versão 01
  @PostMapping
  public Cliente cadastrar(@RequestBody Cliente cliente ) {
        return service.salva(cliente );
  }

    @PostMapping("/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrarv2(@RequestBody Cliente cliente ) {
        return service.salva(cliente );
    }

    @PostMapping("/v3")
    public ResponseEntity<Cliente> cadastrov3(@RequestBody Cliente cliente, UriComponentsBuilder builder ) {

        final Cliente clienteSalvo = service.salva(cliente);

        final URI uri = builder
                     .path("/clientes/{id}")
                     .buildAndExpand(clienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteSalvo );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable Integer id, @RequestBody Cliente cliente) {

        if (service.naoExisteClienteCom(id ) ) {
            return ResponseEntity.notFound().build();

        } else {
            cliente.setId(id);
            Cliente clienteAtualizado = service.salva(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Cliente> optional = service.buscaPor(id );

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
