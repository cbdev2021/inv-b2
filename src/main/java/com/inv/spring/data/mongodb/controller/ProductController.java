package com.inv.spring.data.mongodb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inv.spring.data.mongodb.model.Product;
import com.inv.spring.data.mongodb.model.Sequence;
import com.inv.spring.data.mongodb.repository.ProductRepository;
import com.inv.spring.data.mongodb.repository.SequenceRepository;

// @CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS })
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final SequenceRepository sequenceRepository;
    private final ProductRepository productRepository;

    public ProductController(SequenceRepository sequenceRepository, ProductRepository productRepository) {
        this.sequenceRepository = sequenceRepository;
        this.productRepository = productRepository;
    }

    // @PostMapping("/add-product")
    // public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    // try {
    // // Encuentra y actualiza el documento de la secuencia, incrementando el valor
    // en 1
    // Sequence sequence = sequenceRepository.findById("sequenceProductId")
    // .orElseThrow(() -> new RuntimeException("No se pudo encontrar la
    // secuencia"));
    // int sequenceValue = sequence.getSequenceValue() + 1;
    // sequence.setSequenceValue(sequenceValue);
    // sequenceRepository.save(sequence);

    // // Establece el productId del producto con el valor actualizado de la
    // secuencia
    // product.setProductId(sequenceValue);

    // // Guarda el nuevo producto
    // Product newProduct = productRepository.save(product);

    // return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    // }
    // }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            // Encuentra y actualiza el documento de la secuencia, incrementando el valor en
            // 1
            Sequence sequence = sequenceRepository.findById("sequenceProductId")
                    .orElseThrow(() -> new RuntimeException("No se pudo encontrar la secuencia"));
            int sequenceValue = sequence.getSequenceValue() + 1;
            sequence.setSequenceValue(sequenceValue);
            sequenceRepository.save(sequence);

            // Establece el productId del producto con el valor actualizado de la secuencia
            product.setProductId(sequenceValue);

            // Guarda el nuevo producto
            Product newProduct = productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // @PostMapping("/add-product")
    // public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    // try {
    // Product newProduct = productRepository.save(product);
    // return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    // @PutMapping("/update-product/{id}")
    // public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
    // @RequestBody Product product) {
    // try {
    // if (productRepository.existsById(id)) {
    // product.setId(id);
    // Product updatedProduct = productRepository.save(product);
    // return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product updatedProduct) {
        try {
            Optional<Product> productData = productRepository.findById(id);

            if (productData.isPresent()) {
                Product existingProduct = productData.get();
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setDescription(updatedProduct.getDescription());
                existingProduct.setPrice(updatedProduct.getPrice());
                // Agrega otras propiedades según sea necesario

                Product savedProduct = productRepository.save(existingProduct);

                System.out.println("mensaje");

                System.out.println(savedProduct);

                return new ResponseEntity<>(savedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @DeleteMapping("/delete-product/{id}")
    // public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String
    // id) {
    // try {
    // productRepository.deleteById(id);
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    // @DeleteMapping("/delete-product/{id}")
    // public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("id")
    // String id) {
    // Map<String, String> response = new HashMap<>();
    // try {
    // productRepository.deleteById(id);
    // response.put("message", "Product eliminado con éxito");
    // return new ResponseEntity<>(response, HttpStatus.OK);
    // } catch (Exception e) {
    // response.put("message", "Error al eliminar el producto");
    // return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    // @DeleteMapping("/delete-product/{id}")
    // public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("id")
    // String id,
    // @RequestHeader("Authorization") String token) {

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable("id")
    String id) {

    Map<String, String> response = new HashMap<>();
    try {
    Optional<Product> existingProduct = productRepository.findById(id);

    if (existingProduct.isPresent()) {
    productRepository.deleteById(id);
    response.put("message", "Product eliminado con éxito");
    return new ResponseEntity<>(response, HttpStatus.OK);
    } else {
    response.put("message", "No se encontró el producto con ID: " + id);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    } catch (Exception e) {
    response.put("message", "Error al eliminar el producto");
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    // @DeleteMapping("/delete-product")
    // public ResponseEntity<Map<String, String>> deleteProductByProductId(@RequestParam("productId") String productId) {

    //     Map<String, String> response = new HashMap<>();
    //     try {
    //         Optional<Product> existingProduct = productRepository.findByProductId(productId);

    //         if (existingProduct.isPresent()) {
    //             productRepository.deleteByProductId(productId);
    //             response.put("message", "Producto eliminado con éxito");
    //             return new ResponseEntity<>(response, HttpStatus.OK);
    //         } else {
    //             response.put("message", "No se encontró el producto con productId: " + productId);
    //             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //         }
    //     } catch (Exception e) {
    //         response.put("message", "Error al eliminar el producto");
    //         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id,
            @RequestHeader("Authorization") String token) {
        try {
            if (productRepository.existsById(id)) {
                Product product = productRepository.findById(id).get();
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-products/{idUsuario}")
    public ResponseEntity<Iterable<Product>> getProductsByUserId(@PathVariable("idUsuario") String idUsuario,
            @RequestHeader("Authorization") String token) {
        try {
            Iterable<Product> products = productRepository.findByIdUsuario(idUsuario);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product-amount/{productId}")
    public ResponseEntity<Product> updateProductAmount(@PathVariable("productId") String productId,
            @RequestBody Product product,
            @RequestHeader("Authorization") String token) {
        try {
            if (productRepository.existsById(productId)) {
                Product existingProduct = productRepository.findById(productId).get();
                existingProduct.setAmount(product.getAmount());
                Product updatedProduct = productRepository.save(existingProduct);
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
