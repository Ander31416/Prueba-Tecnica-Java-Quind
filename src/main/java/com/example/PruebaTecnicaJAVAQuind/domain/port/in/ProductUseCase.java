package com.example.PruebaTecnicaJAVAQuind.domain.port.in;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    Product createProduct(Product product);
    Optional<Product> getProduct(Long id);
    List<Product> getAllProducts();
    Optional<Product> updateProduct(Long id, Product updateProduct);
    void deleteProduct(Long id);
}
