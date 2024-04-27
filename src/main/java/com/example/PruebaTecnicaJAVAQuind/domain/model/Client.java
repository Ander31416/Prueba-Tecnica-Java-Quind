package com.example.PruebaTecnicaJAVAQuind.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime birthDate;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    /*
    id,
Tipo de identificación, número de identificación, nombres, apellido, correo electrónico,
fecha de nacimiento, fecha de creación, fecha de modificación.
     */
}
