package com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ClientEntity;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.utils.UtilsClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientMapper {

    private final UtilsClient utilsClient;

    public ClientEntity fromDomainModel(Client client){

        return new ClientEntity(client.getIdClient(), client.getIdType(), client.getIdNumber(), client.getNames(),
                client.getLastNames(), client.getEmail(), client.getBirthDate(), client.getCreationDate()/*utilsClient.getCreationDate(client)*/,
                client.getModificationDate()/*utilsClient.getModificationDate(client)*/);
    }

    public Client toDomainModel(ClientEntity clientEntity){
        return new Client(clientEntity.getIdClient(), clientEntity.getIdType(), clientEntity.getIdNumber(),
                clientEntity.getNames(), clientEntity.getLastNames(), clientEntity.getEmail(),
                clientEntity.getBirthDate(), clientEntity.getCreationDate(), clientEntity.getModificationDate());
    }
}
