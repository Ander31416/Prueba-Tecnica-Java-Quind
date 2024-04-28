package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ClientInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientInput clientInput){
        Client client = clientService.toDomainModel(clientInput);

        Client createdClient = new Client();
        try{
             createdClient = clientService.createClient(client);
         }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
         }
        return new ResponseEntity<>(createdClient, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        return clientService.getClient(clientId)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> tasks = clientService.getAllClients();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Client> updateClient(@RequestBody ClientInput clientInput){
        Client updateClient = clientService.toDomainModel(clientInput);

        return clientService.updateClient(updateClient)
                    .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{ClientId}")
    public ResponseEntity<Object> deleteClientById(@PathVariable Long ClientId) {
        if (clientService.deleteClient(ClientId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
