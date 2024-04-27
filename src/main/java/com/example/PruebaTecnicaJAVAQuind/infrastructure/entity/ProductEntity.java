package com.example.PruebaTecnicaJAVAQuind.infrastructure.entity;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;
    private String accountType;
    private int accountNumber;
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
