package com.example.PruebaTecnicaJAVAQuind.domain.port.in;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface ClientUseCase {
    Client createClient(Client client);
    Optional<Client> getClient(Long id);
    List<Client> getAllClients();
    Optional<Client> updateClient(Long id, Client updateClient);
    boolean deleteClient(Long id);
}
