package com.example.openapi.client;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T13:48:23+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client dtoToEntity(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setFirstname( clientDTO.getFirstname() );
        client.setLastname( clientDTO.getLastname() );
        client.setClientId( clientDTO.getClientId() );
        client.setEmail( clientDTO.getEmail() );

        return client;
    }

    @Override
    public List<Client> dtosToEntities(List<ClientDTO> clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( clientDTO.size() );
        for ( ClientDTO clientDTO1 : clientDTO ) {
            list.add( dtoToEntity( clientDTO1 ) );
        }

        return list;
    }

    @Override
    public ClientDTO entityToDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setFirstname( client.getFirstname() );
        clientDTO.setLastname( client.getLastname() );
        clientDTO.setEmail( client.getEmail() );
        clientDTO.setClientId( client.getClientId() );

        return clientDTO;
    }

    @Override
    public List<ClientDTO> entitiesToDtos(List<Client> client) {
        if ( client == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( client.size() );
        for ( Client client1 : client ) {
            list.add( entityToDto( client1 ) );
        }

        return list;
    }
}
