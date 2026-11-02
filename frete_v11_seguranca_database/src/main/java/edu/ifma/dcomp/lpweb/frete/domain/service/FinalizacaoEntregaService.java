package edu.ifma.dcomp.lpweb.frete.domain.service;


import edu.ifma.dcomp.lpweb.frete.domain.model.Entrega;
import edu.ifma.dcomp.lpweb.frete.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizacaoEntregaService {

	private final EntregaRepository entregaRepository;
	private final BuscaEntregaService buscaEntregaService;

	@Autowired
	public FinalizacaoEntregaService(EntregaRepository entregaRepository,
									 BuscaEntregaService buscaEntregaService) {
		this.entregaRepository = entregaRepository;
		this.buscaEntregaService = buscaEntregaService;
	}


	@Transactional
	public Entrega finalizar(Integer entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finaliza();
		
		return entregaRepository.save(entrega );
	}
	
}