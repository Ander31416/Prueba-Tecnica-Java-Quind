package com.example.Prueba.Tecnica.JAVA.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.ClientController;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ClientInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ProductInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClientControllerTest {
    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    LocalDateTime creationDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    LocalDateTime modificationDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    LocalDateTime customDateTime = LocalDateTime.of(2004, 3, 3, 10, 30, 0);
    Date birthDate = Date.from(customDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    Product product = new Product(1L, "cuenta corriente", 3334567890L, "activa", 1000, false, creationDate, modificationDate, "John Doe", 1L);
    ProductInput productInput = new ProductInput(1L, "cuenta corriente", 3334567890L, "activa", false, "John Doe", 1L);
    Client client = new Client(1L, "ID", 123, "John", "Doe",
            "john.doe@example.com", birthDate, LocalDateTime.now(), LocalDateTime.now());
    ClientInput clientInput = new ClientInput();

    @Test
    void createdClient(){
        when(clientService.toDomainModel(clientInput)).thenReturn(client);
        when(clientService.createClient(client)).thenReturn(client);

        ResponseEntity<Object> result = clientController.createClient(clientInput);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }

    @Test
    void getClientById(){
        when(clientService.getClient(1L)).thenReturn(Optional.ofNullable(client));

        ResponseEntity<Client> result = clientController.getClientById(1L);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }

    @Test
    void updateClient(){
        when(clientService.toDomainModel(clientInput)).thenReturn(client);
        when(clientService.updateClient(client)).thenReturn(Optional.ofNullable(client));

        ResponseEntity<Object> result = clientController.updateClient(clientInput);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }

    @Test
    void deleteClientById(){
        when(productService.deleteClientById(1L)).thenReturn(true);

        ResponseEntity<Object> result = clientController.deleteClientById(1L);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }
}
