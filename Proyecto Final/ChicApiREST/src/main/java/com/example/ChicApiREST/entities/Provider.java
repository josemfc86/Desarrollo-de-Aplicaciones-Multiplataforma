package com.example.ChicApiREST.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {

    @Id
    @Column(name = "provider_user")
    private String providerUser;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String phone;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_provider_direction"), nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_provider_image"), nullable = false)
    private Image image;

    @Column(name = "business_type", nullable = false)
    @ElementCollection(targetClass = BusinessType.class)
    @Enumerated(EnumType.STRING)
    private Set<BusinessType> businessType = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "providers_services",
            joinColumns = @JoinColumn(name = "provider_provider_user", foreignKey = @ForeignKey(name = "fk_provider_service"), nullable = false),
            inverseJoinColumns = @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_provider")))
    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
//    @JoinTable(name = "providers_appointments",
//            joinColumns = @JoinColumn(name="provider_user", foreignKey = @ForeignKey(name = "fk_provider_appointment")),
//            inverseJoinColumns = @JoinColumn(name="appointment_id", foreignKey = @ForeignKey(name = "fk_appointment_provider")) )
    private List<Appointment> appointments = new ArrayList<>();

    public Provider() {
    }

    public Provider(String providerUser, String password, String email, String phone, String name, Address address, Image image, Set<BusinessType> businessType) {
        this.providerUser = providerUser;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.image = image;
        this.businessType = businessType;
    }

    public String getProviderUser() {
        return providerUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<BusinessType> getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Set<BusinessType> businessType) {
        this.businessType = businessType;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

}
