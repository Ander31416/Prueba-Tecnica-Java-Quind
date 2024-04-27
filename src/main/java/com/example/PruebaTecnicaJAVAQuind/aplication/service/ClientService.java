package com.example.PruebaTecnicaJAVAQuind.aplication.service;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class ClientService implements ClientUseCase {

    private final ClientUseCase clientUseCase;

    @Override
    public Client createClient(Client client) {
        return clientUseCase.createClient(client);
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientUseCase.getClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientUseCase.getAllClients();
    }

    @Override
    public Optional<Client> updateClient(Long id, Client updateClient) {
        return clientUseCase.updateClient(id, updateClient);
    }

    @Override
    public boolean deleteClient(Long id) {
        return clientUseCase.deleteClient(id);
    }
}
