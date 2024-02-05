package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product> findByIdUsuario(String idUsuario);
}
