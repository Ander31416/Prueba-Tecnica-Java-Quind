package com.example.PruebaTecnicaJAVAQuind.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    private long idProduct;
    private String accountType;
    private long accountNumber;
    private String state;
    private int balance;
    private boolean exemptGMF;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private String accountOwner;
    @ManyToOne
    @JoinColumn(name = "idClient")
    private ClientEntity clientEntity;

    public boolean getExemptGMF() {
        return exemptGMF;
    }
}
