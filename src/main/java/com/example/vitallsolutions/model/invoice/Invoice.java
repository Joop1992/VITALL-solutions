package com.example.vitallsolutions.model.invoice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    private String id;
    private String clientId;
    private String userId;

    public Invoice(String clientId) {
        this.clientId = clientId;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    @Column(name = "clientId", nullable = false)
    public String getClientId() {
        return clientId;
    }

    @Column(name = "userId", nullable = false)
    public String getUserId() {
        return userId;
    }
}
