package com.example.vitallsolutions.model.invoice;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoiceLines")
public class InvoiceLine {
    private String id;
    private String invoiceId;
    private String invoiceLineDescription;
    private double hours;
    private double pricePerHour;
    private String taxType;
    private double taxPercentage;

    public InvoiceLine(String invoiceId, String invoiceLineDescription, double hours, double pricePerHour, String taxType, double taxPercentage) {
        this.invoiceId = invoiceId;
        this.invoiceLineDescription = invoiceLineDescription;
        this.hours = hours;
        this.pricePerHour = pricePerHour;
        this.taxType = taxType;
        this.taxPercentage = taxPercentage;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    @Column(name = "invoiceId", nullable = false)
    public String getInvoiceId() {
        return invoiceId;
    }

    @Column(name = "invoiceLineDescription", nullable = false)
    public String getInvoiceLineDescription() {
        return invoiceLineDescription;
    }

    @Column(name = "hours", nullable = false)
    public Double getHours() {
        return hours;
    }

    @Column(name = "pricePerHour", nullable = false)
    public Double getPricePerHour() {
        return pricePerHour;
    }

    @Column(name = "taxType", nullable = false)
    public String getTaxType() {
        return taxType;
    }

    @Column(name = "taxPercentage", nullable = false)
    public Double getTaxPercentage() {
        return taxPercentage;
    }
}

