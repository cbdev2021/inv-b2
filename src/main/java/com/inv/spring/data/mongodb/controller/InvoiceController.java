package com.inv.spring.data.mongodb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inv.spring.data.mongodb.model.Invoice;
import com.inv.spring.data.mongodb.repository.InvoiceRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/add-invoice")
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        try {
            Invoice newInvoice = invoiceRepository.save(invoice);
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-invoice/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") String id, @RequestBody Invoice updatedInvoice) {
        try {
            Optional<Invoice> invoiceData = invoiceRepository.findById(id);

            if (invoiceData.isPresent()) {
                Invoice existingInvoice = invoiceData.get();
                // Update other properties as needed
                existingInvoice.setInvoiceType(updatedInvoice.getInvoiceType());
                existingInvoice.setDateIssue(updatedInvoice.getDateIssue());
                existingInvoice.setSubTotal(updatedInvoice.getSubTotal());
                existingInvoice.setTaxes(updatedInvoice.getTaxes());
                existingInvoice.setCustomer(updatedInvoice.getCustomer());
                existingInvoice.setPaymentSell(updatedInvoice.getPaymentSell());
                existingInvoice.setProvider(updatedInvoice.getProvider());
                existingInvoice.setPaymentBuy(updatedInvoice.getPaymentBuy());

                Invoice savedInvoice = invoiceRepository.save(existingInvoice);

                return new ResponseEntity<>(savedInvoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-invoice/{id}")
    public ResponseEntity<Map<String, String>> deleteInvoice(@PathVariable("id") String id) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<Invoice> existingInvoice = invoiceRepository.findById(id);

            if (existingInvoice.isPresent()) {
                invoiceRepository.deleteById(id);
                response.put("message", "Invoice eliminado con éxito");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "No se encontró la factura con ID: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar la factura");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-invoice/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") String id) {
        try {
            if (invoiceRepository.existsById(id)) {
                Invoice invoice = invoiceRepository.findById(id).get();
                return new ResponseEntity<>(invoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
