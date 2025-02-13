package com.example.openapi.client;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client dtoToEntity(ClientDTO clientDTO);

    List<Client> dtosToEntities(List<ClientDTO> clientDTO);
    ClientDTO entityToDto(Client client);

    List<ClientDTO> entitiesToDtos(List<Client> client);
}
