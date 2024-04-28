package com.example.PruebaTecnicaJAVAQuind.aplication.usecase;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ClientRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client createClient(Client client) {
        return clientRepositoryPort.save(client);
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepositoryPort.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryPort.findAll();
    }

    @Override
    public Optional<Client> updateClient(Client updateClient) {
        return clientRepositoryPort.update(updateClient);
    }

    @Override
    public boolean deleteClient(Long id) {
        return clientRepositoryPort.deleteById(id);
    }
}
