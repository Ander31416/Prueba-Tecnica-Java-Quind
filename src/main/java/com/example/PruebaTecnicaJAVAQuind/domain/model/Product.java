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
public class Product {

    private long idProduct;
    private String accountType;
    private int accountNumber;
    private String state;
    private int balance;
    private boolean exemptGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String accountOwner;
    private long idClient;

    public boolean getExemptGMF() {
        return this.exemptGMF;
    }

    /*
    id, tipo de cuenta, número de cuenta, estado (activa, inactiva, cancelada), saldo,
exenta GMF, fecha de creación, fecha modificación, usuario al que pertenece la cuenta.
     */
}
