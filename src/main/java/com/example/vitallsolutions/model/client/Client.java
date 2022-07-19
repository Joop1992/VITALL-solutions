package com.example.vitallsolutions.model.client;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    private String id;
    private String userId;
    private String email;
    private String name;
    private String address;
    private String contactName;

    public Client(String name, String email, String address, String userId, String contactName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactName = contactName;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    @Column(name = "userId", nullable = false)
    public String getUserId() {
        return userId;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "address", nullable = true)
    public String getAddress() {
        return this.address;
    }

    @Column(name = "contactName", nullable = false)
    public String getContactName() {
        return this.contactName;
    }
}
