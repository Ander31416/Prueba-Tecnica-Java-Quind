package com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ClientEntity;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper{

    private static ClientService clientService;
    private static ClientMapper clientMapper;

    @Autowired
    public ProductMapper(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    public static ProductEntity fromDomainModel(Product product){
        ClientEntity clientEntity = clientService.getClient(product.getIdClient()).map(clientMapper::fromDomainModel)
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
