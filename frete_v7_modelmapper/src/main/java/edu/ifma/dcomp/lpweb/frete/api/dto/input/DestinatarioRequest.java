package edu.ifma.dcomp.lpweb.frete.api.dto.input;


import jakarta.validation.constraints.NotBlank;

public record DestinatarioRequest(
		@NotBlank String nome,
		@NotBlank String logradouro,
		@NotBlank String numero,
		@NotBlank String complemento,
		@NotBlank String bairro
) { }