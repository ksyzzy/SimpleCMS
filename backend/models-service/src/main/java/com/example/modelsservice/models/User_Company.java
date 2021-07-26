package com.example.modelsservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_company")
public class User_Company extends BaseEntity implements Serializable, Eligible {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Company company;

    @Column(name = "owner")
    @JsonProperty(value = "owner", access = JsonProperty.Access.READ_ONLY)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean owner = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
