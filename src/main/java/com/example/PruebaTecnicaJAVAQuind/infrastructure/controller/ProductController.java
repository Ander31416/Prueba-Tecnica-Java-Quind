package com.example.PruebaTecnicaJAVAQuind.infrastructure.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ProductInput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductInput productInput){
        Product product = productService.toDomainModel(productInput);

        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return productService.getProduct(productId)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product = productService.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductInput productInput){
        Product updateProduct = productService.toDomainModel(productInput);

        return productService.updateProduct(updateProduct)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long productId) {
        if (productService.deleteProduct(productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
