package com.example.modelsservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @ApiModelProperty(notes = "ID of the entity", name = "id", required = true, value = "id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected long id;

    @Column(name = "creation_date")
    @CreationTimestamp
    @JsonProperty(value = "creationDate", access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
