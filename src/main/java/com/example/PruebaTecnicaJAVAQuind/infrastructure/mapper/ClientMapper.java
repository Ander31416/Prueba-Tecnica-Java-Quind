package com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public static ClientEntity fromDomainModel(Client client){
        return new ClientEntity(client.getIdClient(), client.getIdType(), client.getIdNumber(), client.getNames(),
                client.getLastNames(), client.getEmail(), client.getBirthDate(), client.getCreationDate(),
                client.getModificationDate());
    }

    public Client toDomainModel(ClientEntity clientEntity){
        return new Client(clientEntity.getIdClient(), clientEntity.getIdType(), clientEntity.getIdNumber(),
                clientEntity.getNames(), clientEntity.getLastNames(), clientEntity.getEmail(),
                clientEntity.getBirthDate(), clientEntity.getCreationDate(), clientEntity.getModificationDate());
    }
}
