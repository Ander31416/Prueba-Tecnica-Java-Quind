package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.ConsignationInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.TranceferenceInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionOutput.TransferenceOutput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.WithdrawalInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.mapperToProduct.MapperToProduct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private MapperToProduct mapperToProduct;
    private ProductService productService;

    @PutMapping
    @RequestMapping("/consignacion")
    public ResponseEntity<Object> Consignation(@RequestBody ConsignationInput consignationInput){
        try{
            Product product = mapperToProduct.consignationToProductMapper(consignationInput);

            Product updatedProduct = productService.updateProduct(product).orElse(null);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    @RequestMapping("/retiro")
    public ResponseEntity<Object> Withdrawal(@RequestBody WithdrawalInput withdrawalInput){
        try{
            Product product = mapperToProduct.withdrawalToProductMapper(withdrawalInput);

            Product updatedProduct = productService.updateProduct(product).orElse(null);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping
    @RequestMapping("/transferencia")
    public ResponseEntity<Object> Transference(@RequestBody TranceferenceInput tranceferenceInput){
        try{
            Map<String, Product> products = mapperToProduct.transferenceToProductMapper(tranceferenceInput);
            TransferenceOutput transferenceOutput = new TransferenceOutput();

            productService.productIsCorrect(products.get("receivingProduct"));
            productService.productIsCorrect(products.get("shippingProduct"));

            Product updatedReceivingProduct = productService.updateProduct(products.get("receivingProduct")).orElse(null);
            Product updatedShippingProduct = productService.updateProduct(products.get("shippingProduct")).orElse(null);

            transferenceOutput.setReceivingProduct(updatedReceivingProduct);
            transferenceOutput.setShippingProduct(updatedShippingProduct);
            return new ResponseEntity<>(transferenceOutput, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
