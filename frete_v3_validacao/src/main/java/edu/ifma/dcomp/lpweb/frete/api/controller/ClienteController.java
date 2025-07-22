package edu.ifma.dcomp.lpweb.frete.api.controller;

import edu.ifma.dcomp.lpweb.frete.domain.model.Cliente;
import edu.ifma.dcomp.lpweb.frete.domain.service.ClienteService;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPorV3(@PathVariable Integer id) {
       return service.buscaPor(id)
               .map(ResponseEntity::ok )   //.map(cliente -> ResponseEntity.ok(cliente))
               .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Cliente> cadastro(@Valid @RequestBody Cliente cliente, UriComponentsBuilder builder ) {

        final Cliente clienteSalvo = service.salva(cliente);

        final URI uri = builder
                     .path("/clientes/{id}")
                     .buildAndExpand(clienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteSalvo );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualiza(@Valid @PathVariable Integer id, @RequestBody Cliente cliente) {

        if (service.naoExisteClienteCom(id ) ) {
            return ResponseEntity.notFound().build();

        } else {
            cliente.setId(id);
            Cliente clienteAtualizado = service.salva(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Integer id) {
        Optional<Cliente> optional = service.buscaPor(id );

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
