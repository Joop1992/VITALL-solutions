package com.example.vitallsolutions.model.user;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    private String id;
    private String email;
    private String name;
    private Double fee;
    private Date createdAt;
    private String status;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fee = 10.0;
        this.status = UserStatus.INACTIVE;
        this.createdAt = new Date();
    }

    @Id
    public String getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "fee", nullable = false)
    public Double getFee() {
        return this.fee;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    @Column(name = "createdAt", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }
}
