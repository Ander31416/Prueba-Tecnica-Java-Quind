package com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ClientEntity;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ProductEntity;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends ProductEntity {

    private static ClientRepository clientRepository;
    private static ClientMapper clientMapper;

    public static ProductEntity fromDomainModel(Product product){
        ClientEntity clientEntity = clientRepository.findById(product.getIdClient())
                .orElse(null);

        return new ProductEntity(product.getIdProduct(), product.getAccountType(), product.getAccountNumber(),
                product.getState(), product.getBalance(), product.getExemptGMF(), product.getCreationDate(),
                product.getModificationDate(), product.getAccountOwner(), clientEntity);
    }

    public Product toDomainModel(ProductEntity productEntity){

        return new Product(productEntity.getIdProduct(), productEntity.getAccountType(),
                productEntity.getAccountNumber(), productEntity.getState(), productEntity.getBalance(),
                productEntity.getExemptGMF(), productEntity.getCreationDate(), productEntity.getModificationDate(),
                productEntity.getAccountOwner(), productEntity.getClientEntity().getIdClient());
    }
}
