package com.example.PruebaTecnicaJAVAQuind.aplication.usecase;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ProductUseCase;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public Optional<Product> updateProduct(Product updateProduct) {
        return productRepositoryPort.update(updateProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepositoryPort.deleteById(id);
    }
}
