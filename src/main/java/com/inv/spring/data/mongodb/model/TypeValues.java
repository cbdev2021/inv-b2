package com.inv.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "type_values")
public class TypeValues {
    @Id
    private String id;

    private String typevalue;
    private String subtype;
    private String description;
    private String idUsuario;

    // Constructores

    public TypeValues() {
    }

    public TypeValues(String typevalue, String subtype, String description, String idUsuario) {
        this.typevalue = typevalue;
        this.subtype = subtype;
        this.description = description;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypevalue() {
        return typevalue;
    }

    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Otros métodos, como toString, hashCode, equals, etc.
}
