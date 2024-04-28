package com.example.Prueba.Tecnica.JAVA.service;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ClientInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
    @Mock
    private ClientUseCase clientUseCase;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    LocalDateTime customDateTime = LocalDateTime.of(2004, 3, 3, 10, 30, 0);
    Date birthDate = Date.from(customDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    Client client = new Client(1L, "ID", 123, "John", "Doe",
            "john.doe@example.com", birthDate, LocalDateTime.now(), LocalDateTime.now());

    @Test
    void toDomainModel_ReturnsClientThatExistInDB() {
        // Given
        ClientInput clientInput = new ClientInput(1L, "ID", 123, "John", "Doe", "john.doe@example.com", birthDate);

        when(clientUseCase.getClient(1L)).thenReturn(Optional.of(client));

        // When
        Client result = clientService.toDomainModel(clientInput);

        // Then
        assertNotNull(result);
        assertEquals(client.getIdClient(), result.getIdClient());
        assertEquals(client.getIdType(), result.getIdType());
        assertEquals(client.getIdNumber(), result.getIdNumber());
        assertEquals(client.getNames(), result.getNames());
        assertEquals(client.getLastNames(), result.getLastNames());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getBirthDate(), result.getBirthDate());
        assertEquals(client.getCreationDate(), result.getCreationDate());
        assertEquals(client.getModificationDate(), result.getModificationDate());
    }

    @Test
    void calculateAge() {
        int date = ClientService.calculateAge(birthDate);

        assertEquals(date, 20);
    }

    @Test
    void createClient(){
        when(clientUseCase.getClient(1L)).thenReturn(Optional.of(client));

        Client result = clientService.createClient(client);

        // Then
        assertNotNull(result);
        assertEquals(client.getIdClient(), result.getIdClient());
        assertEquals(client.getIdType(), result.getIdType());
        assertEquals(client.getIdNumber(), result.getIdNumber());
        assertEquals(client.getNames(), result.getNames());
        assertEquals(client.getLastNames(), result.getLastNames());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getBirthDate(), result.getBirthDate());
        assertEquals(client.getCreationDate(), result.getCreationDate());
        assertEquals(client.getModificationDate(), result.getModificationDate());
    }

    @Test
    void createClient_isLessThan18(){
        client.setBirthDate(new Date());

        when(clientUseCase.getClient(1L)).thenReturn(Optional.of(client));

        String Exception = "";

        try{
            Client result = clientService.createClient(client);
        } catch(Exception e){
            Exception = e.getMessage();
        }

        assertEquals(Exception, "Menores de edad no permitidos");
    }

    @Test
    void getClient(){
        when(clientUseCase.getClient(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClient(1L).orElse(null);

        // Then
        assertNotNull(result);
        assertEquals(client.getIdClient(), result.getIdClient());
        assertEquals(client.getIdType(), result.getIdType());
        assertEquals(client.getIdNumber(), result.getIdNumber());
        assertEquals(client.getNames(), result.getNames());
        assertEquals(client.getLastNames(), result.getLastNames());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getBirthDate(), result.getBirthDate());
        assertEquals(client.getCreationDate(), result.getCreationDate());
        assertEquals(client.getModificationDate(), result.getModificationDate());
    }

    @Test
    void geAllClients(){
        when(clientUseCase.getAllClients()).thenReturn(List.of(client));

        List<Client> results = clientService.getAllClients();

        for(Client result : results){
            // Then
            assertNotNull(result);
            assertEquals(client.getIdClient(), result.getIdClient());
            assertEquals(client.getIdType(), result.getIdType());
            assertEquals(client.getIdNumber(), result.getIdNumber());
            assertEquals(client.getNames(), result.getNames());
            assertEquals(client.getLastNames(), result.getLastNames());
            assertEquals(client.getEmail(), result.getEmail());
            assertEquals(client.getBirthDate(), result.getBirthDate());
            assertEquals(client.getCreationDate(), result.getCreationDate());
            assertEquals(client.getModificationDate(), result.getModificationDate());
        }
    }

    @Test
    void updateClient(){
        Client updatedClient = client;
        updatedClient.setModificationDate(LocalDateTime.now());

        when(clientUseCase.updateClient(client)).thenReturn(Optional.of(updatedClient));

        Client result = clientService.updateClient(client).orElse(null);

        // Then
        assertNotNull(result);
        assertEquals(updatedClient.getIdClient(), result.getIdClient());
        assertEquals(updatedClient.getIdType(), result.getIdType());
        assertEquals(updatedClient.getIdNumber(), result.getIdNumber());
        assertEquals(updatedClient.getNames(), result.getNames());
        assertEquals(updatedClient.getLastNames(), result.getLastNames());
        assertEquals(updatedClient.getEmail(), result.getEmail());
        assertEquals(updatedClient.getBirthDate(), result.getBirthDate());
        assertEquals(updatedClient.getCreationDate(), result.getCreationDate());
        assertEquals(updatedClient.getModificationDate(), result.getModificationDate());
    }

    @Test
    void deleteClient(){
        when(clientUseCase.deleteClient(1L)).thenReturn(true);

        Boolean wasDeleted = clientService.deleteClient(1L);

        assertEquals(wasDeleted, true);
    }
    
    @Test
    void deleteClientById(){

    }
}
