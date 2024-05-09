package com.example.ChicApiREST.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime date;

    @Column(name = "total_price")
    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "user", foreignKey = @ForeignKey(name = "fk_appointment_user"), nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "provider", foreignKey = @ForeignKey(name = "fk_appointment_provider"), nullable = false)
    private Provider provider;

    @ManyToMany()
    private Set<Service> services = new HashSet<>();

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime date, Long totalPrice, User user, Provider provider) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.user = user;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUser() {
        return user.getUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProvider() {
        return provider.getName();
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
