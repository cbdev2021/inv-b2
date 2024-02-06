package com.inv.spring.data.mongodb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inv.spring.data.mongodb.model.ProductInvoice;
import com.inv.spring.data.mongodb.repository.ProductInvoiceRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/product-invoices")
public class ProductInvoiceController {

    @Autowired
    private ProductInvoiceRepository productInvoiceRepository;

    @PostMapping("/add-product-invoice")
    public ResponseEntity<ProductInvoice> addProductInvoice(@RequestBody ProductInvoice productInvoice) {
        try {
            ProductInvoice newProductInvoice = productInvoiceRepository.save(productInvoice);
            return new ResponseEntity<>(newProductInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PutMapping("/update-product-invoice/{id}")
    // public ResponseEntity<ProductInvoice>
    // updateProductInvoice(@PathVariable("id") String id, @RequestBody
    // ProductInvoice updatedProductInvoice) {
    // try {
    // Optional<ProductInvoice> productInvoiceData =
    // productInvoiceRepository.findById(id);

    // if (productInvoiceData.isPresent()) {
    // ProductInvoice existingProductInvoice = productInvoiceData.get();
    // // Update other properties as needed
    // existingProductInvoice.setInvoiceType(updatedProductInvoice.getInvoiceType());
    // existingProductInvoice.setDateIssue(updatedProductInvoice.getDateIssue());
    // existingProductInvoice.setSubTotal(updatedProductInvoice.getSubTotal());
    // existingProductInvoice.setTaxes(updatedProductInvoice.getTaxes());
    // existingProductInvoice.setCustomer(updatedProductInvoice.getCustomer());
    // existingProductInvoice.setPaymentSell(updatedProductInvoice.getPaymentSell());
    // existingProductInvoice.setProvider(updatedProductInvoice.getProvider());
    // existingProductInvoice.setPaymentSell(updatedProductInvoice.getPaymentSell());

    // ProductInvoice savedProductInvoice =
    // productInvoiceRepository.save(existingProductInvoice);

    // return new ResponseEntity<>(savedProductInvoice, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PutMapping("/update-product-invoice/{id}")
    public ResponseEntity<ProductInvoice> updateProductInvoice(@PathVariable("id") String id,
            @RequestBody ProductInvoice updatedProductInvoice) {
        try {
            Optional<ProductInvoice> productInvoiceData = productInvoiceRepository.findById(id);

            if (productInvoiceData.isPresent()) {
                ProductInvoice existingProductInvoice = productInvoiceData.get();

                // SOLO HAY 5 AGREGAR LOS
                existingProductInvoice.setInvoiceType(updatedProductInvoice.getInvoiceType());
                existingProductInvoice.setDateIssue(updatedProductInvoice.getDateIssue());
                existingProductInvoice.setPrice(updatedProductInvoice.getPrice());
                existingProductInvoice.setAmount(updatedProductInvoice.getAmount());
                existingProductInvoice.setUtility(updatedProductInvoice.getUtility());
                //
                existingProductInvoice.setIdUsuario(updatedProductInvoice.getIdUsuario()
                existingProductInvoice.setInvoiceID(updatedProductInvoice.getInvoiceID()
                existingProductInvoice.setProductId(updatedProductInvoice.getProductId()
                existingProductInvoice.setName(updatedProductInvoice.getName()
                existingProductInvoice.setDescription(updatedProductInvoice.getDescription()

                ProductInvoice savedProductInvoice = productInvoiceRepository.save(existingProductInvoice);

                return new ResponseEntity<>(savedProductInvoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product-invoice/{id}")
    public ResponseEntity<Map<String, String>> deleteProductInvoice(@PathVariable("id") String id) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<ProductInvoice> existingProductInvoice = productInvoiceRepository.findById(id);

            if (existingProductInvoice.isPresent()) {
                productInvoiceRepository.deleteById(id);
                response.put("message", "ProductInvoice eliminado con éxito");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "No se encontró el ProductInvoice con ID: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar el ProductInvoice");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product-invoice/{id}")
    public ResponseEntity<ProductInvoice> getProductInvoice(@PathVariable("id") String id) {
        try {
            if (productInvoiceRepository.existsById(id)) {
                ProductInvoice productInvoice = productInvoiceRepository.findById(id).get();
                return new ResponseEntity<>(productInvoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product-invoices/{idUsuario}")
    public ResponseEntity<Iterable<ProductInvoice>> getProductInvoicesByUserId(
            
            @PathVariable("idUsuario") String idUsuario) {
        try {
            Iterable<ProductInvoice> productInvoices = productInvoiceRepository.findByIdUsuario(idUsuario);
            return new ResponseEntity<>(productInvoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
