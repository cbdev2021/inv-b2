package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.ProductInvoice;
import org.springframework.data.repository.CrudRepository;

public interface ProductInvoiceRepository extends CrudRepository<ProductInvoice, String> {
    // Add custom query methods if needed
}
