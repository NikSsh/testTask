package com.my.testproject.model.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * User class represents the model in database
 */
@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 45)
    private String firstName;
    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    public User() {

    }

    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    @JsonIgnore
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty(value = "age")
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (hashCode() != user.hashCode()) return false;
        return Objects.equals(id, user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }
}
