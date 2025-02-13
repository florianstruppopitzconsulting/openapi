package com.example.openapi.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/client")
public class ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDTO findClientByClientId(@PathVariable @NotNull Long clientId) {
        return clientService.findByClientId(clientId);
    }


    @DeleteMapping(value = "/{clientId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteClientByClientId(@PathVariable @NotNull Long clientId) {
        clientService.deleteByClientId(clientId);
    }


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createNewClient(clientDTO);
    }


    @PutMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDTO updateClient(@PathVariable @NotNull Long clientId, @RequestBody @NotNull ClientDTO dto) {
        return clientService.updateClientByClientId(clientId, dto);
    }


}
