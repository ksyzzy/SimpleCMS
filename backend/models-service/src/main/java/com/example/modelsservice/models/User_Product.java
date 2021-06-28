package com.example.modelsservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_product")
public class User_Product extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Product product;

    @Column(name = "amount")
    @NotNull
    private Integer amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
