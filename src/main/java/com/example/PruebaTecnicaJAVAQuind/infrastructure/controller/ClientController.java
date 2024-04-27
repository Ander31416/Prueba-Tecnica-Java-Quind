package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
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
    public ResponseEntity<Client> createClient(@RequestBody Client client){
         Client createdClient = clientService.createClient(client);
         return new ResponseEntity<>(createdClient, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getTaskById(@PathVariable Long clientId) {
        return clientService.getClient(clientId)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllTasks(){
        List<Client> tasks = clientService.getAllClients();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateTask(@PathVariable Long clientId, @RequestBody Client updateClient){
        return clientService.updateClient(clientId, updateClient)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteClientById(@PathVariable Long taskId) {
        if (clientService.deleteClient(taskId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
