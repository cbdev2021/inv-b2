package com.inv.spring.data.mongodb.repository;

// import org.springframework.data.mongodb.repository.MongoRepository;

// import com.inv.spring.data.mongodb.model.Sequence;

// public interface SequenceRepository extends MongoRepository<Sequence, String> {
//     // Sequence findByName(String name);
//     // Sequence findByNombre(String nombre);
//     Sequence findBy_id(String id);

// }

// import org.springframework.data.mongodb.repository.MongoRepository;
// import com.inv.spring.data.mongodb.model.Sequence;

// public interface SequenceRepository extends MongoRepository<Sequence, String> {
//     Sequence findBySequenceProductId(String sequenceProductId); // Cambiado de 'findById' a 'findBySequenceProductId'
    
// }

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inv.spring.data.mongodb.model.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence, String> {
    Sequence findByid(String id);
}
