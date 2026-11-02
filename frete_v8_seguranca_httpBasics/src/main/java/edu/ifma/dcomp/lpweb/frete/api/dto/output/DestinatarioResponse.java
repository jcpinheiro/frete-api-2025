package edu.ifma.dcomp.lpweb.frete.api.dto.output;

public record DestinatarioResponse(
		String nome,
		String logradouro,
		String numero,
		String complemento,
		String bairro
) { }