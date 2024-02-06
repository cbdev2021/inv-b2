package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    // Add custom query methods if needed
}
