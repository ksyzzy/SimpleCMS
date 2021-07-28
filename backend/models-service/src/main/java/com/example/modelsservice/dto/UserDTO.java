package com.example.modelsservice.dto;

import com.example.modelsservice.models.Eligible;

import java.lang.reflect.Field;
import java.util.*;

public class UserDTO implements Eligible {

    private long id;

    private Date creationDate;

    private String login;

    private String email;

    private String firstName;

    private String lastName;

    private boolean admin;

    private Date lastLogin;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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


    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, login, email, firstName, lastName, admin, lastLogin);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        UserDTO that = (UserDTO) object;
        if (!(that.getCreationDate().compareTo(this.getCreationDate()) == 0) &&
                !(that.getLastLogin().compareTo(this.lastLogin) == 0)) {
            return false;
        }
        System.out.println(this.hashCode());
        System.out.println(that.hashCode());
        return id == that.getId() &&
                login.equals(that.getLogin()) &&
                email.equals(that.getEmail()) &&
                firstName.equals(that.getFirstName()) &&
                lastName.equals(that.getLastName()) &&
                admin == that.isAdmin();
     }
}
