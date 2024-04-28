package com.example.PruebaTecnicaJAVAQuind.infrastructure.config;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.aplication.usecase.ClientUseCaseImpl;
import com.example.PruebaTecnicaJAVAQuind.aplication.usecase.ProductUseCaseImpl;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ClientRepositoryPort;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ProductRepositoryPort;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository.ClientRepositoryAdapter;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository.ProductRepositoryAdapter;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper.ClientMapper;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ClientService clientService(ClientRepositoryPort clientRepositoryPort){
        return new ClientService(
                new ClientUseCaseImpl(clientRepositoryPort)
        );
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort,
                                         ClientRepositoryPort clientRepositoryPort){
        return new ProductService(
                new ProductUseCaseImpl(productRepositoryPort),
                new ClientUseCaseImpl(clientRepositoryPort)
        );
    }

    @Bean
    public ClientRepositoryPort clientRepositoryPort(ClientRepositoryAdapter clientRepositoryAdapter){
        return clientRepositoryAdapter;
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductRepositoryAdapter productRepositoryAdapter){
        return productRepositoryAdapter;
    }

    @Bean
    public ProductMapper productMapper(ClientService clientService,
                                       ClientMapper clientMapper){
        return new ProductMapper(clientService, clientMapper);
    }

}
