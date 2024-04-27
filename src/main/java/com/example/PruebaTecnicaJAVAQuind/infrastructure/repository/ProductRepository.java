package com.example.PruebaTecnicaJAVAQuind.infrastructure.repository;

import com.example.PruebaTecnicaJAVAQuind.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
