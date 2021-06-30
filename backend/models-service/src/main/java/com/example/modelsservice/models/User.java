package com.example.modelsservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "login", unique = true)
    @NotNull
    private String login;

    @Column(name = "password")
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<User_Company> companies;

    @Column(name = "email")
    @Email(message = "Must be a valid e-mail address")
    @NotNull
    @Size(max = 100, message = "E-mail cannot be longer that 100 characters")
    private String email;

    @Column(name = "first_name")
    @NotNull
    @Size(max = 100, message = "First name cannot be longer than 100 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(max = 100, message = "Last name cannot be longer than 100 characters")
    private String lastName;

    @Column(name = "is_admin", nullable = false)
    @JsonProperty(value = "admin", access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean admin = false;

    @Column(name = "last_login_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @OneToMany(mappedBy = "owner")
    private Set<Product> products;

    @OneToMany(mappedBy = "user")
    private Set<User_Product> transactions;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User_Product> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<User_Product> transactions) {
        this.transactions = transactions;
    }

    public Set<User_Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<User_Company> companies) {
        this.companies = companies;
    }
}