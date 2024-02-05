package com.inv.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequences")
public class Sequence {
    @Id
    private String id;

    private int sequence_value;

    // Constructores

    public Sequence() {
    }

    public Sequence(int sequence_value) {
        this.sequence_value = sequence_value;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSequence_value() {
        return sequence_value;
    }

    public void setSequence_value(int sequence_value) {
        this.sequence_value = sequence_value;
    }

    // Otros métodos, como toString, hashCode, equals, etc.
}
