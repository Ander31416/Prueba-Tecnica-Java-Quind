package com.example.PruebaTecnicaJAVAQuind.aplication.service;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductUseCase productUseCase;

    @Override
    public Product createProduct(Product product) {
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
    public Optional<Product> updateProduct(Long id, Product updateProduct) {
        return productUseCase.updateProduct(id, updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productUseCase.deleteProduct(id);
    }
}
