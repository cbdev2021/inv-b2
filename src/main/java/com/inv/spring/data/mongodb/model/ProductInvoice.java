package com.inv.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "product_invoices")
public class ProductInvoice {
    @Id
    private String id;

    private String idUsuario;
    private String invoiceType;
    private int invoiceID;
    private int productId;
    private String name;
    private String description;
    private double price;
    private int amount;
    private Date dateIssue;
    private double utility;

    // Constructores

    public ProductInvoice() {
    }

    public ProductInvoice(String idUsuario, String invoiceType, int invoiceID, int productId, String name, String description, double price, int amount, Date dateIssue, double utility) {
        this.idUsuario = idUsuario;
        this.invoiceType = invoiceType;
        this.invoiceID = invoiceID;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.dateIssue = dateIssue;
        this.utility = utility;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

 
}
