package com.example.modelsservice.models;

import com.example.modelsservice.enums.CompanyType;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "company")
    private Set<User_Company> companies;

    @Column(name = "name")
    @NotBlank
    @Size(max = 100, message = "Company name cannot exceed the limit of 100 characters")
    private String name;

    @Column(name = "address")
    @NotBlank
    @Size(max = 100, message = "Address length cannot exceed 100 characters")
    private String address;

    @Column(name = "nip", unique = true)
    @Min(value = 10, message = "NIP must be exactly 10 digits long")
    @Max(value = 10, message = "NIP must be exactly 10 digits long")
    private int nip;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    @Column(name = "founded_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date founded;

    @OneToMany(mappedBy = "ownedByCompany")
    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<User_Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<User_Company> companies) {
        this.companies = companies;
    }
}
