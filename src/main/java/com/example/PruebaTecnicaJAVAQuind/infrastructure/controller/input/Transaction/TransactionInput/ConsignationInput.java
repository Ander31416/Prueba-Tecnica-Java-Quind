package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsignationInput {
    private long idProduct;
    private long cash;
}
