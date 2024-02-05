package com.inv.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "registers")
public class Register {
    @Id
    private String id;

    private String tipoRegistro;
    private String idUsuario;
    private Date fecha;
    private double monto;

    // Constructores

    public Register() {
    }

    public Register(String tipoRegistro, String idUsuario, Date fecha, double monto) {
        this.tipoRegistro = tipoRegistro;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.monto = monto;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    // Otros métodos, como toString, hashCode, equals, etc.
}
