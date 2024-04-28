package com.example.Prueba.Tecnica.JAVA.controller;

import com.example.PruebaTecnicaJAVAQuind.aplication.service.ProductService;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Client;
import com.example.PruebaTecnicaJAVAQuind.domain.model.Product;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.ProductController;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.TransactionController;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ClientInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.ProductInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.ConsignationInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.TransferenceInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.WithdrawalInput;
import com.example.PruebaTecnicaJAVAQuind.infrastructure.controller.input.Transaction.TransactionInput.mapperToProduct.MapperToProduct;
import jakarta.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    @Mock
    private MapperToProduct mapperToProduct;

    @Mock
    private ProductService productService;

    @InjectMocks
    private TransactionController transactionController;

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
    ClientInput clientInput = new ClientInput();
    ConsignationInput consignationInput = new ConsignationInput();
    WithdrawalInput withdrawalInput = new WithdrawalInput();
    TransferenceInput transferenceInput = new TransferenceInput();
    Map<String, Product> productsMap = new HashMap<>();

    @Test
    void consignation(){
        when(mapperToProduct.consignationToProductMapper(consignationInput)).thenReturn(product);
        when(productService.updateProduct(product)).thenReturn(Optional.ofNullable(product));

        ResponseEntity<Object> result = transactionController.Consignation(consignationInput);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }

    @Test
    void withdrawal(){
        when(mapperToProduct.withdrawalToProductMapper(withdrawalInput)).thenReturn(product);
        when(productService.updateProduct(product)).thenReturn(Optional.ofNullable(product));

        ResponseEntity<Object> result = transactionController.Withdrawal(withdrawalInput);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }

    @Test
    void transference(){
        when(mapperToProduct.transferenceToProductMapper(transferenceInput)).thenReturn(productsMap);
        when(productService.productIsCorrect(product)).thenReturn(true);
        when(productService.updateProduct(product)).thenReturn(Optional.ofNullable(product));

        ResponseEntity<Object> result = transactionController.Transference(transferenceInput);

        assertEquals(result.getStatusCode().toString(), "200 OK");
    }
}
