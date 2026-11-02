package edu.ifma.dcomp.lpweb.frete.api.dto.output;

import edu.ifma.dcomp.lpweb.frete.domain.model.StatusEntrega;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EntregaResponse(
		Integer id,
		ClienteIdNomeResponse cliente,
		DestinatarioResponse destinatario,
		BigDecimal taxa,
		StatusEntrega status,
		LocalDateTime dataPedido
) { }