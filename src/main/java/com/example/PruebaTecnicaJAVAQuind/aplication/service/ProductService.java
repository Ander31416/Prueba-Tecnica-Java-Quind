package com.example.PruebaTecnicaJAVAQuind.aplication.service;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ProductUseCase;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.error.CustomException;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ProductInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductUseCase productUseCase;
    private final ClientUseCase clientUseCase;

    public Product toDomainModel(ProductInput productInput) {
        Product product = productUseCase.getProduct(productInput.getIdProduct()).orElse(null);
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime modificationDate = LocalDateTime.now();
        String state = "activa";
        int balance = 0;

        if(product != null){
            creationDate = product.getCreationDate();
            modificationDate = product.getModificationDate();
            state = product.getState();
            balance = product.getBalance();
        }

        return new Product(productInput.getIdProduct(), productInput.getAccountType(), productInput.getAccountNumber(),
                state, balance, productInput.getExemptGMF(), creationDate, modificationDate,
                productInput.getAccountOwner(), productInput.getIdClient());
    }

    @Override
    public Product createProduct(Product product) {
        productIsCorrect(product);
        return productUseCase.createProduct(product);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productUseCase.getProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productUseCase.getAllProducts();
    }

    @Override
    public Optional<Product> updateProduct(Product updateProduct) {
        updateProduct.setModificationDate(LocalDateTime.now());
        return Optional.ofNullable(this.createProduct(updateProduct));
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productUseCase.deleteProduct(id);
    }

    public boolean deleteClientById(Long id) {
        List<Product> productsByIdClient = this.getProductsByIdClient(id);

        if(productsByIdClient == null){
            return clientUseCase.deleteClient(id);
        }

        List<Product> uncanceledProducts = productsByIdClient.stream()
                .filter(product -> !Objects.equals(product.getState(), "cancelada"))
                .toList();

        if(uncanceledProducts.isEmpty()){
            return clientUseCase.deleteClient(id);
        }

        throw new CustomException(HttpStatus.FORBIDDEN, "El cliente no puede ser eliminado si tiene cuentas disponibles");
    }

    public List<Product> getProductsByIdClient(long idClient){
        return productUseCase.getAllProducts().stream()
                .filter(product -> product.getIdClient() == idClient)
                .toList();
    }

    public boolean productIsCorrect(Product product){
        List<String> AccountTypes = Arrays.asList("cuenta corriente","cuenta de ahorros");

        if(!AccountTypes.contains(product.getAccountType().toLowerCase())){
            throw new CustomException(HttpStatus.FORBIDDEN, "Solo los productos 'cuenta corriente' y " +
                    "'cuenta de ahorros' son permitidos");
        }

        Client clientOfProduct = clientUseCase.getClient(product.getIdClient()).orElse(null);

        if(clientOfProduct == null){
            throw new CustomException(HttpStatus.FORBIDDEN, "El producto a crear no tiene ningun cliente relacionado");
        }

        if(product.getAccountType().toLowerCase().equals("cuenta de ahorros") && product.getBalance() < 0){
            throw new CustomException(HttpStatus.FORBIDDEN, "Para una cuenta de ahorros el saldo no puede ser menor a 0");
        }

        if(String.valueOf(product.getAccountNumber()).length() != 10){
            throw new CustomException(HttpStatus.FORBIDDEN, "El número de cuenta debe tener exactamente 10 digitos");
        }

        int firstTwoDigits = (int) (product.getAccountNumber()/Math.pow(10, 8));

        if(product.getAccountType().toLowerCase().equals("cuenta de ahorros") && firstTwoDigits != 53){
            throw new CustomException(HttpStatus.FORBIDDEN, "Los 2 primeros digitos del número de cuenta de una " +
                    "cuenta de ahorros deben ser 53");
        }

        if(product.getAccountType().toLowerCase().equals("cuenta corriente") && firstTwoDigits != 33){
            throw new CustomException(HttpStatus.FORBIDDEN, "Los 2 primeros digitos del número de cuenta de una " +
                    "cuenta corriente deben ser 33");
        }

        if(product.getState().toLowerCase().equals("cancelada") && product.getBalance() != 0){
            throw new CustomException(HttpStatus.FORBIDDEN, "Para que una cuenta se encuentre cancelada en necesario " +
                    "que el saldo sea igual a 0");
        }

        return true;
    }
}
