package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WithdrawalInput {
    private long idProduct;
    private long cash;
}
