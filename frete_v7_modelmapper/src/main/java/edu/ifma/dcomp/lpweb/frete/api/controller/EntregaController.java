package edu.ifma.dcomp.lpweb.frete.api.controller;


import edu.ifma.dcomp.lpweb.frete.api.dto.input.EntregaRequest;
import edu.ifma.dcomp.lpweb.frete.api.dto.output.EntregaResponse;
import edu.ifma.dcomp.lpweb.frete.api.mapper.EntregaMapperAdapter;
import edu.ifma.dcomp.lpweb.frete.domain.model.Entrega;
import edu.ifma.dcomp.lpweb.frete.domain.service.BuscaEntregaService;
import edu.ifma.dcomp.lpweb.frete.domain.service.ClienteService;
import edu.ifma.dcomp.lpweb.frete.domain.service.FinalizacaoEntregaService;
import edu.ifma.dcomp.lpweb.frete.domain.service.SolicitacaoEntregaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private final SolicitacaoEntregaService solicitacaoEntregaService;
	private final ClienteService clienteService;
	private final BuscaEntregaService buscaEntregaService;
	private final FinalizacaoEntregaService finalizacaoEntregaService;
	private final EntregaMapperAdapter entregaMapperAdapter;

	@Autowired
	public EntregaController(SolicitacaoEntregaService solicitacaoEntregaService,
                             ClienteService clienteService,
                             BuscaEntregaService buscaEntregaService,
                             FinalizacaoEntregaService finalizacaoEntregaService,
							 EntregaMapperAdapter entregaMapperAdapter) {
		this.solicitacaoEntregaService = solicitacaoEntregaService;
		this.clienteService = clienteService;
		this.buscaEntregaService = buscaEntregaService;
		this.finalizacaoEntregaService = finalizacaoEntregaService;
		this.entregaMapperAdapter = entregaMapperAdapter;
	}

	@GetMapping
	public List<EntregaResponse> listar() {
		return entregaMapperAdapter.toCollectionResponse(buscaEntregaService.todas());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaResponse solicitar(@Valid @RequestBody EntregaRequest entregaInput) {
		Entrega novaEntrega = entregaMapperAdapter.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

		return entregaMapperAdapter.toEntregaResponse(entregaSolicitada );
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntregaResponse> buscar(@PathVariable Integer id ) {

		// código funcional
		return buscaEntregaService.buscaPor(id)
				.map(entregaMapperAdapter::toEntregaResponse)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

		/*    Optional<Entrega> entregaOptional = buscaEntregaService.buscaPor(id);
  		      if (entregaOptional.isEmpty()) {
        			return ResponseEntity.notFound().build();
    		  }
              EntregaResponse response = entregaMapperAdapter.toEntregaResponse(entregaOptional.get());
             return ResponseEntity.ok(response);
        */
	}
	
	//@PutMapping("/{id}/finalizacao")
	@PatchMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public EntregaResponse finalizar(@PathVariable Integer id) {
		return  entregaMapperAdapter.toEntregaResponse(
				finalizacaoEntregaService.finalizar(id ) );
	}
	
}