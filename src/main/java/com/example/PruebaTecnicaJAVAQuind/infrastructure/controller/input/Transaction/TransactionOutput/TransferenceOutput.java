package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionOutput;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class TransferenceOutput {

    private Product receivingProduct;
    private Product shippingProduct;

    public Product getReceivingProduct() {
        return receivingProduct;
    }

    public void setReceivingProduct(Product receivingProduct) {
        this.receivingProduct = receivingProduct;
    }

    public Product getShippingProduct() {
        return shippingProduct;
    }

    public void setShippingProduct(Product shippingProduct) {
        this.shippingProduct = shippingProduct;
    }
}
