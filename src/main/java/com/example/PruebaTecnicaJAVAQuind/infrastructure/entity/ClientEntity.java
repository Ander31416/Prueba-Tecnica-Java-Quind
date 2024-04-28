package com.example.PruebaTecnicaJAVAQuind.infrastructure.entity;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class ClientEntity {

    @Id
    private long idClient;
    private String idType;
    private int idNumber;
    private String names;
    private String lastNames;
    private String email;
    private Date birthDate;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
