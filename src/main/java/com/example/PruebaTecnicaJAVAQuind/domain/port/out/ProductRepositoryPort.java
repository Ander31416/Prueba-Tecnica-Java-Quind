package com.example.PruebaTecnicaJAVAQuind.domain.port.out;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();

    List<Product> findAllByIdClient(Long idClient);

    Optional<Product> update(Product product);
    void deleteById(Long id);
}
