package com.example.openapi.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    ClientDTO createNewClient(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientDTO));
        return clientMapper.entityToDto(client);
    }

    public Collection<ClientDTO> getAllClients() {
        return clientMapper.entitiesToDtos(clientRepository.findAll());
    }

    public ClientDTO findByClientId(Long clientId) {
        Optional<Client> optionalClient =  getClientByClientId(clientId);

        if(optionalClient.isPresent()) {
            return clientMapper.entityToDto(optionalClient.get());
        }
        return new ClientDTO();
    }
    @Transactional
    public ClientDTO updateClientByClientId(Long clientId, ClientDTO dto) {
        Optional<Client> optionalClient =  getClientByClientId(clientId);

        if(optionalClient.isPresent()) {
            Client currentClient = optionalClient.get();
            currentClient.setEmail(dto.getEmail());
            currentClient.setFirstname(dto.getFirstname());
            currentClient.setLastname(dto.getLastname());
            currentClient = clientRepository.save(currentClient);
            return clientMapper.entityToDto(currentClient);
        }
        return new ClientDTO();
    }

    private Optional<Client> getClientByClientId(Long clientId) {
        return clientRepository.findByClientId(clientId);
    }

    @Transactional
    public void deleteByClientId(Long clientId) {
        clientRepository.deleteByClientId(clientId);
    }
}
