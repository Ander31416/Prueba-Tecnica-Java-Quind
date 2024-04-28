package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TranceferenceInput {
    private long idProductReceivingAccount;
    private long idProductShippingAccount;
    private long cash;
}
