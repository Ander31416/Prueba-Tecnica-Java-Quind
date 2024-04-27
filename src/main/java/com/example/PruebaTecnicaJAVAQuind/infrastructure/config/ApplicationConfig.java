package com.example.PruebaTecnicaJAVAQuind.infrastructure.config;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ClientService;
import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.aplication.usecase.ClientUseCaseImpl;
import com.example.PruebaTecnicaJAVAQuind.aplication.usecase.ProductUseCaseImpl;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ClientRepositoryPort;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ExternalServicePort;
import com.example.PruebaTecnicaJAVAQuind.domain.port.out.ProductRepositoryPort;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.ExternalServiceAdapter;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository.ClientRepositoryAdapter;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.adapter.repository.ProductRepositoryAdapter;
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
    public ProductService productService(ProductRepositoryPort productRepositoryPort){
        return new ProductService(
                new ProductUseCaseImpl(productRepositoryPort)
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
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }

}
