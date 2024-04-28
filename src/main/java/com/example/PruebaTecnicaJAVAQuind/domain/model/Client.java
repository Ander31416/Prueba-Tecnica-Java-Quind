package com.example.PruebaTecnicaJAVAQuind.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
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
