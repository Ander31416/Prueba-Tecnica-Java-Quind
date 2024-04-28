package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.mapperToProduct;

import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.error.CustomException;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.ConsignationInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.TranceferenceInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.WithdrawalInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.mapper.ProductMapper;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapperToProduct {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public MapperToProduct(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product consignationToProductMapper(ConsignationInput consignationInput) {
        Product product = productRepository.findById(consignationInput.getIdProduct())
                .map(productMapper::toDomainModel).orElse(null);

        if(product == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Producto no encontrado");
        }

        product.setBalance((int) (product.getBalance()+consignationInput.getCash()));

        return product;
    }

    public Map<String, Product> transferenceToProductMapper(TranceferenceInput tranceferenceInput){
        Map<String, Product> transferenceProducts = new HashMap<>();

        Product receivingProduct = productRepository.findById(tranceferenceInput.getIdProductReceivingAccount())
                .map(productMapper::toDomainModel).orElse(null);

        Product shippingProduct = productRepository.findById(tranceferenceInput.getIdProductShippingAccount())
                .map(productMapper::toDomainModel).orElse(null);

        if(receivingProduct == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "cuenta de recepción no encontrada");
        }

        if(shippingProduct == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "cuenta de envío no encontrada");
        }

        receivingProduct.setBalance((int) (receivingProduct.getBalance()+tranceferenceInput.getCash()));
        shippingProduct.setBalance((int) (shippingProduct.getBalance()-tranceferenceInput.getCash()));

        transferenceProducts.put("receivingProduct", receivingProduct);
        transferenceProducts.put("shippingProduct", shippingProduct);
        
        return transferenceProducts;
    }

    public Product withdrawalToProductMapper(WithdrawalInput withdrawalInput){
        Product product = productRepository.findById(withdrawalInput.getIdProduct())
                .map(productMapper::toDomainModel).orElse(null);

        if(product == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Producto no encontrado");
        }

        product.setBalance((int) (product.getBalance()-withdrawalInput.getCash()));

        return product;
    }
}
