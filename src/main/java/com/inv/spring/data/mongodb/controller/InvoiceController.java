package com.inv.spring.data.mongodb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inv.spring.data.mongodb.model.Invoice;
import com.inv.spring.data.mongodb.model.Sequence;
import com.inv.spring.data.mongodb.repository.InvoiceRepository;
import com.inv.spring.data.mongodb.repository.SequenceRepository;

// @CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private SequenceRepository sequenceRepository;

    @PostMapping("/add-invoice")
    // public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice,
    @RequestHeader("Authorization") String token) {
        try {
            Invoice newInvoice = invoiceRepository.save(invoice);
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-invoice/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") String id, @RequestBody Invoice updatedInvoice,
    @RequestHeader("Authorization") String token) {
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
    public ResponseEntity<Map<String, String>> deleteInvoice(@PathVariable("id") String id,
    @RequestHeader("Authorization") String token) {
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
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") String id,
    @RequestHeader("Authorization") String token) {
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

    @GetMapping("/get-invoices/{idUsuario}")
    public ResponseEntity<Iterable<Invoice>> getInvoicesByUserId(@PathVariable("idUsuario") String idUsuario,
    @RequestHeader("Authorization") String token) {
        try {
            Iterable<Invoice> invoices = invoiceRepository.findByIdUsuario(idUsuario);
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generate-id/{invoiceType}")
    public ResponseEntity<Map<String, Integer>> generateId(@PathVariable("invoiceType") String invoiceType) {
        try {
            String sequenceId;
            if (invoiceType.equals("Purchase")) {
                sequenceId = "sequencePurchaseId";
            } else if (invoiceType.equals("Sales")) {
                sequenceId = "sequenceSaleId";
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            Optional<Sequence> sequenceData = sequenceRepository.findById(sequenceId);

            if (sequenceData.isPresent()) {
                Sequence sequence = sequenceData.get();
                sequence.setSequenceValue(sequence.getSequenceValue() + 1);
                sequenceRepository.save(sequence);

                Map<String, Integer> response = new HashMap<>();
                response.put("sequence_value", sequence.getSequenceValue());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
