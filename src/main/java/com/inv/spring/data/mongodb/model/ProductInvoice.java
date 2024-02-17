package com.inv.spring.data.mongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "productinvoices")
public class ProductInvoice {
    @Id
    private String id;

    @JsonProperty("idUsuario")
    private String idUsuario;

    @JsonProperty("invoiceType")
    private String invoiceType;

    @JsonProperty("invoiceID")
    private String invoiceID;

    @JsonProperty("productId")
    private int productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private double price;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("dateIssue")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy", timezone = "UTC")
    private Date dateIssue;

    @JsonProperty("utility")
    private double utility;


    // @Id // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private String id;
    // @NonNull
    // private String idUsuario;
    // @NonNull
    // private String invoiceType;
    // @NonNull
    // private int invoiceID;
    // @NonNull
    // private int productId;
    // @NonNull
    // private String name;
    // @NonNull
    // private String description;
    // @NonNull
    // private double price;
    // @NonNull
    // private int amount;
    // @NonNull
    // private Date dateIssue;
    // @NonNull
    // private double utility;

    // Constructores

    public ProductInvoice() {
    }

    public ProductInvoice(String idUsuario, String invoiceType, String invoiceID, int productId, String name,
            String description, double price, int amount, Date dateIssue, double utility) {
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

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
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
