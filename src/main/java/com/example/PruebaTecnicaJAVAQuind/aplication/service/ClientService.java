package com.example.PruebaTecnicaJAVAQuind.aplication.service;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.error.CustomException;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ClientInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class ClientService implements ClientUseCase {

    private final ClientUseCase clientUseCase;

    public Client toDomainModel(ClientInput clientInput) {

        Client client = clientUseCase.getClient(clientInput.getIdClient()).orElse(null);
        LocalDateTime creationDate = null;
        LocalDateTime modificationDate = null;

        if(client != null){
            creationDate = client.getCreationDate();
            modificationDate = client.getModificationDate();
        }

        return new Client(clientInput.getIdClient(), clientInput.getIdType(), clientInput.getIdNumber(),
                clientInput.getNames(), clientInput.getLastNames(), clientInput.getEmail(), clientInput.getBirthDate(),
                creationDate, modificationDate);
    }

    public static int calculateAge(Date birthDate) {
        LocalDate birthLocalDate = new java.sql.Date(birthDate.getTime()).toLocalDate();

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(birthLocalDate, currentDate);

        return period.getYears();
    }

    @Override
    public Client createClient(Client client) {

        if(calculateAge(client.getBirthDate()) < 18){
            throw new CustomException(HttpStatus.FORBIDDEN, "Menores de edad no permitidos");
        }

        Client clientIfExist = clientUseCase.getClient(client.getIdClient()).orElse(null);

        if(clientIfExist == null){
            client.setCreationDate(LocalDateTime.now());
            client.setModificationDate(LocalDateTime.now());
        } else {
            return clientIfExist;
        }

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
    public Optional<Client> updateClient(Client updateClient) {
        if(calculateAge(updateClient.getBirthDate()) < 18){
            throw new CustomException(HttpStatus.FORBIDDEN, "Menores de edad no permitidos");
        }

        updateClient.setModificationDate(LocalDateTime.now());

        return clientUseCase.updateClient(updateClient);
    }

    @Override
    public boolean deleteClient(Long id) {
        return clientUseCase.deleteClient(id);
    }
}
