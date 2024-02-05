package com.inv.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;

    private int invoiceID;
    private String invoiceType;
    private String idUsuario;
    private Date dateIssue;
    private double subTotal;
    private double taxes;
    private String customer;
    private String paymentSell;
    private String provider;
    private String paymentBuy;

    // Constructores

    public Invoice() {
    }

    public Invoice(int invoiceID, String invoiceType, String idUsuario, Date dateIssue, double subTotal, double taxes, String customer, String paymentSell, String provider, String paymentBuy) {
        this.invoiceID = invoiceID;
        this.invoiceType = invoiceType;
        this.idUsuario = idUsuario;
        this.dateIssue = dateIssue;
        this.subTotal = subTotal;
        this.taxes = taxes;
        this.customer = customer;
        this.paymentSell = paymentSell;
        this.provider = provider;
        this.paymentBuy = paymentBuy;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPaymentSell() {
        return paymentSell;
    }

    public void setPaymentSell(String paymentSell) {
        this.paymentSell = paymentSell;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPaymentBuy() {
        return paymentBuy;
    }

    public void setPaymentBuy(String paymentBuy) {
        this.paymentBuy = paymentBuy;
    }

    // Otros métodos, como toString, hashCode, equals, etc.
}
