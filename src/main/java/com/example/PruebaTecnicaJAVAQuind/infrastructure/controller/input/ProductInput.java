package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductInput {

    private long idProduct;
    private String accountType;
    private long accountNumber;
    private String state;
    private boolean exemptGMF;
    private String accountOwner;
    private long idClient;

    public boolean getExemptGMF() {
        return this.exemptGMF;
    }
}
