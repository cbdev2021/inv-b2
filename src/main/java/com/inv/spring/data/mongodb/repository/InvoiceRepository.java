package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.Invoice;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    // Add custom query methods if needed
    Iterable<Invoice> findByIdUsuario(String idUsuario);
    void deleteById(String id);
    
    Optional<Invoice> findByInvoiceID(int invoiceID);
    void deleteByInvoiceID(int invoiceID);
    

}
