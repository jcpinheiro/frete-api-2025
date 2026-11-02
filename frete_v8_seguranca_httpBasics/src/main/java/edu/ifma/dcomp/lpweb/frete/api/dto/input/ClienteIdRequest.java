package edu.ifma.dcomp.lpweb.frete.api.dto.input;


import jakarta.validation.constraints.NotNull;

public record ClienteIdRequest(@NotNull Integer id) {  }