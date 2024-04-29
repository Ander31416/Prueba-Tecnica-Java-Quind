package com.example.Prueba.Tecnica.JAVA.service;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ClientUseCase;
import com.example.PruebaTecnicaJAVAQuind.domain.port.in.ProductUseCase;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ProductInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Mock
    private ClientUseCase clientUseCase;

    @Mock
    private ProductUseCase productUseCase;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    LocalDateTime creationDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    LocalDateTime modificationDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    LocalDateTime customDateTime = LocalDateTime.of(2004, 3, 3, 10, 30, 0);
    Date birthDate = Date.from(customDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    Product product = new Product(1L, "cuenta corriente", 3334567890L, "activa", 1000, false, creationDate, modificationDate, "John Doe", 1L);
    ProductInput productInput = new ProductInput(1L, "cuenta corriente", 3334567890L, "activa", false, "John Doe", 1L);
    Client client = new Client(1L, "ID", 123, "John", "Doe",
            "john.doe@example.com", birthDate, LocalDateTime.now(), LocalDateTime.now());

    @Test
    void toDomainModel_ProductFound(){
        when(productUseCase.getProduct(1l)).thenReturn(Optional.ofNullable(product));

        Product result = productService.toDomainModel(productInput);

        assertNotNull(result);
        assertEquals(product.getIdProduct(), result.getIdProduct());
        assertEquals(product.getAccountType(), result.getAccountType());
        assertEquals(product.getAccountNumber(), result.getAccountNumber());
        assertEquals(product.getState(), result.getState());
        assertEquals(product.getBalance(), result.getBalance());
        assertEquals(product.getExemptGMF(), result.getExemptGMF());
        assertEquals(product.getCreationDate(), result.getCreationDate());
        assertEquals(product.getModificationDate(), result.getModificationDate());
        assertEquals(product.getAccountOwner(), result.getAccountOwner());
        assertEquals(product.getIdClient(), result.getIdClient());
    }

    @Test
    void createProduct(){
        when(productUseCase.createProduct(product)).thenReturn(product);
        when(clientUseCase.getClient(1L)).thenReturn(Optional.ofNullable(client));

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals(product.getIdProduct(), result.getIdProduct());
        assertEquals(product.getAccountType(), result.getAccountType());
        assertEquals(product.getAccountNumber(), result.getAccountNumber());
        assertEquals(product.getState(), result.getState());
        assertEquals(product.getBalance(), result.getBalance());
        assertEquals(product.getExemptGMF(), result.getExemptGMF());
        assertEquals(product.getCreationDate(), result.getCreationDate());
        assertEquals(product.getModificationDate(), result.getModificationDate());
        assertEquals(product.getAccountOwner(), result.getAccountOwner());
        assertEquals(product.getIdClient(), result.getIdClient());
    }

    @Test
    void getProduct() {
        when(productUseCase.getProduct(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L).orElse(null);

        assertNotNull(result);
        assertEquals(product.getIdProduct(), result.getIdProduct());
        assertEquals(product.getAccountType(), result.getAccountType());
        assertEquals(product.getAccountNumber(), result.getAccountNumber());
        assertEquals(product.getState(), result.getState());
        assertEquals(product.getBalance(), result.getBalance());
        assertEquals(product.getExemptGMF(), result.getExemptGMF());
        assertEquals(product.getCreationDate(), result.getCreationDate());
        assertEquals(product.getModificationDate(), result.getModificationDate());
        assertEquals(product.getAccountOwner(), result.getAccountOwner());
        assertEquals(product.getIdClient(), result.getIdClient());
    }

    @Test
    void getAllProducts(){
        when(productUseCase.getAllProducts()).thenReturn(List.of(product));

        List<Product> results = productService.getAllProducts();

        for(Product result : results){
            assertNotNull(result);
            assertEquals(product.getIdProduct(), result.getIdProduct());
            assertEquals(product.getAccountType(), result.getAccountType());
            assertEquals(product.getAccountNumber(), result.getAccountNumber());
            assertEquals(product.getState(), result.getState());
            assertEquals(product.getBalance(), result.getBalance());
            assertEquals(product.getExemptGMF(), result.getExemptGMF());
            assertEquals(product.getCreationDate(), result.getCreationDate());
            assertEquals(product.getModificationDate(), result.getModificationDate());
            assertEquals(product.getAccountOwner(), result.getAccountOwner());
            assertEquals(product.getIdClient(), result.getIdClient());
        }
    }

    @Test
    void updateProduct(){
        when(clientUseCase.getClient(1L)).thenReturn(Optional.ofNullable(client));
        when(productUseCase.createProduct(product)).thenReturn(product);

        Product result = productService.updateProduct(product).orElse(null);

        assertNotNull(result);
        assertEquals(product.getIdProduct(), result.getIdProduct());
        assertEquals(product.getAccountType(), result.getAccountType());
        assertEquals(product.getAccountNumber(), result.getAccountNumber());
        assertEquals(product.getState(), result.getState());
        assertEquals(product.getBalance(), result.getBalance());
        assertEquals(product.getExemptGMF(), result.getExemptGMF());
        assertEquals(product.getCreationDate(), result.getCreationDate());
        assertEquals(product.getAccountOwner(), result.getAccountOwner());
        assertEquals(product.getIdClient(), result.getIdClient());
    }

    @Test
    void deleteProduct(){
        when(productUseCase.deleteProduct(1L)).thenReturn(true);

        Boolean result = productService.deleteProduct(1L);

        assertEquals(result, true);
    }

    @Test
    void deleteClientById(){
        when(clientUseCase.deleteClient(1L)).thenReturn(true);
        when(productUseCase.getAllProducts().stream()
                .filter(product -> product.getIdClient() == 1L)
                .toList()).thenReturn(List.of(product));

        product.setState("cancelada");

        Boolean result = productService.deleteClientById(1L);

        assertEquals(result, true);
    }

    @Test
    void getProductsByIdClient(){
        when(productUseCase.getAllProducts().stream()
                .filter(product -> product.getIdClient() == 1L)
                .toList()).thenReturn(List.of(product));

        List<Product> results = productService.getProductsByIdClient(1L);

        for(Product result : results){
            assertNotNull(result);
            assertEquals(product.getIdProduct(), result.getIdProduct());
            assertEquals(product.getAccountType(), result.getAccountType());
            assertEquals(product.getAccountNumber(), result.getAccountNumber());
            assertEquals(product.getState(), result.getState());
            assertEquals(product.getBalance(), result.getBalance());
            assertEquals(product.getExemptGMF(), result.getExemptGMF());
            assertEquals(product.getCreationDate(), result.getCreationDate());
            assertEquals(product.getModificationDate(), result.getModificationDate());
            assertEquals(product.getAccountOwner(), result.getAccountOwner());
            assertEquals(product.getIdClient(), result.getIdClient());
        }
    }

    @Test
    void productIsCorrect(){
        when(clientUseCase.getClient(1L)).thenReturn(Optional.ofNullable(client));

        Boolean result = productService.productIsCorrect(product, false);

        assertEquals(result, true);
    }
}
