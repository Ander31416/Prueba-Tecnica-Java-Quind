package com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ProductRepositoryPort;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ProductEntity;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper.ProductMapper;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductMapper.fromDomainModel(product);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productMapper.toDomainModel(savedProductEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDomainModel);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByIdClient(Long idClient) {
        return productRepository.findAll().stream()
                .filter(n -> n == productRepository.findById(idClient).orElse(null))
                .map(productMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> update(Product product) {
        if(productRepository.existsById(product.getIdProduct())) {
            ProductEntity productEntity = ProductMapper.fromDomainModel(product);
            ProductEntity updateTaskEntity = productRepository.save(productEntity);
            return Optional.of(productMapper.toDomainModel(updateTaskEntity));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
