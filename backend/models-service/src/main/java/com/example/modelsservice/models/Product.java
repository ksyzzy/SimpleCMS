package com.example.modelsservice.models;

import com.example.modelsservice.enums.ProductType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters long")
    private String name;

    @Column(name = "type")
    @NotNull
    private ProductType type;

    @Column(name = "image", unique = true)
    @Size(min = 1, max = 100, message = "Image name must be between 1 and 100 characters long")
    private String imageFileName;

    @ManyToOne(optional = false)
    private User owner;

    @ManyToOne(optional = false)
    private Company ownedByCompany;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "transactions")
    private Set<User> buyers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Company getOwnedByCompany() {
        return ownedByCompany;
    }

    public void setOwnedByCompany(Company ownedByCompany) {
        this.ownedByCompany = ownedByCompany;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(Set<User> buyers) {
        this.buyers = buyers;
    }
}
