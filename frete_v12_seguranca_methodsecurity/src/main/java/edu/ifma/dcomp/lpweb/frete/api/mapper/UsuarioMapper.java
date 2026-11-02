package edu.ifma.dcomp.lpweb.frete.api.mapper;

import edu.ifma.dcomp.lpweb.frete.api.dto.input.UsuarioDTO;
import edu.ifma.dcomp.lpweb.frete.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
