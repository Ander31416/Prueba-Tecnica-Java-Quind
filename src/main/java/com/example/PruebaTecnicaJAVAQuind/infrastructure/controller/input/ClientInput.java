package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input;

import com.example.PruebaTecnicaJAVAQuind.aplication.usecase.ClientUseCaseImpl;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository.ClientRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class ClientInput{
    private long idClient;
    private String idType;
    private int idNumber;
    private String names;
    private String lastNames;
    private String email;
    private Date birthDate;
}
