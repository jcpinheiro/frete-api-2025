package edu.ifma.dcomp.lpweb.frete.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EntregaRequest(
		@Valid @NotNull ClienteIdRequest cliente,
		@Valid @NotNull DestinatarioRequest destinatario,
		@NotNull BigDecimal taxa
) { }
