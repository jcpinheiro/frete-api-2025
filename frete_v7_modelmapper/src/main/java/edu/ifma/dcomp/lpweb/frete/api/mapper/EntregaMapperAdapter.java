package edu.ifma.dcomp.lpweb.frete.api.mapper;

import edu.ifma.dcomp.lpweb.frete.api.dto.input.EntregaRequest;
import edu.ifma.dcomp.lpweb.frete.api.dto.output.EntregaResponse;
import edu.ifma.dcomp.lpweb.frete.domain.model.Entrega;
import org.mapstruct.Mapper;

import java.util.List;

// para que o Spring injete este mapper como um @Component
@Mapper(componentModel = "spring")
public interface EntregaMapperAdapter {

	EntregaResponse toEntregaResponse(Entrega entrega);
	List<EntregaResponse> toCollectionResponse(List<Entrega> entregas);
	Entrega toEntity(EntregaRequest entregaRequest);
}